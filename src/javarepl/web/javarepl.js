document.write('<div id="console" class="console"></div>')

var session = [];

function createNewSession(expression, snap) {
    var newSession = [];
    newSession.expression = expression;
    newSession.snap = snap;

    $.ajax({type: 'POST',
            async: false,
            url: '/create',
            data: (expression ? "expression=" + expression : "") + "&" + (snap ? "snap=" + snap : "")}
    ).done(function (data) {
            newSession.clientId = data.id;
            newSession.welcomeMessage = data.welcomeMessage
        });

    newSession.requesting = false;

    session = newSession;
}


// return a parameter value from the current URL
function getParam(sname) {
    var params = location.search.substr(location.search.indexOf("?") + 1);
    var sval = "";
    params = params.split("&");
    // split param and value into individual pieces
    for (var i = 0; i < params.length; i++) {
        temp = params[i].split("=");
        if ([temp[0]] == sname) {
            sval = temp[1];
        }
    }
    return sval;
}

function getBaseURL() {
    return location.protocol + "//" + location.hostname +
        (location.port && ":" + location.port) + location.pathname;
}

function closeSession() {
    $.ajax({type: 'POST', async: false, url: '/remove', data: 'id=' + session.clientId});
}


function restartSession() {
    closeSession();
    createNewSession(session.expression, session.snap)
}

function makeSnap() {
    var snapUrl = null;
    $.ajax({type: 'POST', async: false, url: '/snap', data: 'id=' + session.clientId})
        .done(function (data) {
            snapUrl = getBaseURL() + '?snap=' + data.snap;
        }).fail(function (xhr, textStatus, errorThrown) {
            restartSession()
        });

    return snapUrl;
}

function readExpressionLine(line) {
    var expression = null;

    $.ajax({type: 'POST', async: false, url: '/readExpression', data: {id: session.clientId, line: line}})
        .done(function (data) {
            expression = data.expression;
        })
        .fail(function (xhr, textStatus, errorThrown) {
            restartSession()
        });

    return expression;
}


function layoutCompletions(candidates) {
    var max = 0;
    for (var i = 0; i < candidates.length; i++) {
        max = Math.max(max, candidates[i].length);
    }
    max += 2;
    var n = Math.floor(85 / max);
    var buffer = "";
    var col = 0;
    for (i = 0; i < candidates.length; i++) {
        var completion = candidates[i];
        buffer += candidates[i];
        for (var j = completion.length; j < max; j++) {
            buffer += " ";
        }
        if (++col >= n) {
            buffer += "\n";
            col = 0;
        }
    }
    return buffer;
}


$(window).bind('beforeunload', function () {
    closeSession();
});

$(document).ready(function () {
    var console = $('<div>');
    $('#console').prepend(console);

    createNewSession(getParam("expression"), getParam("snap"))

    var controller = console.console({
        promptLabel: 'java> ',
        commandValidate: function (line) {
            return !session.requesting;
        },
        commandHandle: function (line, report) {
            if (line == ":snap") {
                var snapUri = makeSnap();
                $(".jquery-console-inner").append('<div class="jquery-console-message jquery-console-link">' +
                    '<a href="' + snapUri + '" target="_blank">' + snapUri + '</a></div>')
                report([]);
                return [];
            }
            var expression = readExpressionLine(line);

            if (expression) {
                $.post('/execute', {id: session.clientId, expression: expression})
                    .done(function (data) {
                        var messages = [];
                        var hadError = false;

                        for (var i = 0; i < data.logs.length; i++) {
                            var type = data.logs[i].type == "ERROR" ? "jquery-console-message-error" : "jquery-console-message-success";

                            if (data.logs[i].type == "ERROR") {
                                hadError = true;
                            }
                            messages.push({msg: data.logs[i].message, className: type})
                        }

                        if (!hadError) {
                            _gaq.push(["_trackEvent", "console", "evaluation", "success"]);
                        } else {
                            _gaq.push(["_trackEvent", "console", "evaluation", "error"]);
                        }

                        report(messages);
                        session.requesting = false;
                    })
                    .fail(function (xhr, textStatus, errorThrown) {
                        report([
                            {msg: "Session terminated. Starting new session...", className: "jquery-console-message-service-error"}
                        ]);
                        restartSession()
                    });
            } else {
                report([]);
                session.requesting = false;
            }

            return [];

        },
        completeHandle: function (prefix) {
            var completionResult;
            $.ajax({type: 'GET', async: false, cache: false, url: '/completions', data: {id: session.clientId, expression: prefix}})
                .done(function (data) {
                    completionResult = data;
                });


            var candidates = _.map(completionResult.candidates, function (cand) {
                return cand.value;
            });
            var candidatesForms = _.map(completionResult.candidates, function (cand) {
                return cand.forms;
            });
            var promptText = controller.promptText();

            if (candidates.length == 0) {
                return [];
            }

            if (candidates.length == 1) {
                var uniqueForms = _.filter(_.unique(candidatesForms[0]), function (form) {
                    return form != candidates[0]
                });
                var text = controller.promptText().substr(0, parseInt(completionResult.position)) + candidates[0];

                if (uniqueForms.length > 0) {
                    controller.commandResult(layoutCompletions(candidatesForms[0]), "jquery-console-message-completion");
                }
                controller.promptText(text);
                return [];
            }

            controller.commandResult(layoutCompletions(candidates), "jquery-console-message-completion");

            for (var i = candidates[0].length; i > 0; --i) {
                var prefixedCandidatesCount = _.filter(candidates,function (cand) {
                    return i > cand.length ? false : cand.substr(0, i) == candidates[0].substr(0, i)
                }).length;

                if (prefixedCandidatesCount == candidates.length) {
                    controller.promptText(promptText.substr(0, parseInt(completionResult.position)) + candidates[0].substr(0, i));
                    return [];
                }
            }

            controller.promptText(promptText);
            return [];
        },
        welcomeMessage: session.welcomeMessage,
        autofocus: true,
        animateScroll: true,
        promptHistory: true,
        charInsertTrigger: function (keycode, line) {
            return true;
        }
    });
});