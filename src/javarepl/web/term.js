document.write('<div id="terminal" class="terminal-content"></div>');

var session = {};

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

function greetings(term) {
    term.echo(session.welcomeMessage);
    term.echo(' ');
}


function createNewSession(expression, snap) {
    var newSession = [];
    newSession.expression = expression;
    newSession.snap = snap;

    $.ajax({
            type: 'POST',
            async: false,
            url: '/create',
            data: (expression ? "expression=" + expression : "") + "&" + (snap ? "snap=" + snap : "")
        }
    ).done(function (data) {
        newSession.clientId = data.id;
        newSession.welcomeMessage = data.welcomeMessage
    });

    newSession.requesting = false;

    session = newSession;
}

function closeSession() {
    $.ajax({type: 'POST', async: false, url: '/remove', data: 'id=' + session.clientId})
        .fail(function (xhr, textStatus, errorThrown) {/* ignore failure when closing */ });

}


function restartSession(term) {
    term.echo("[[;#CC7832;black]Session terminated. Starting new session...]");
    closeSession();
    createNewSession(session.expression, session.snap)
}

function readExpressionLine(line, term) {
    var expression = null;

    $.ajax({type: 'POST', async: false, url: '/readExpression', data: {id: session.clientId, line: line}})
        .done(function (data) {
            expression = data.expression;
        })
        .fail(function (xhr, textStatus, errorThrown) {
            restartSession(term)
        });

    return expression;
}

function makeSnap(term) {
    var snapUrl = null;
    $.ajax({type: 'POST', async: false, url: '/snap', data: 'id=' + session.clientId})
        .done(function (data) {
            snapUrl = getBaseURL() + '?snap=' + data.snap;
        }).fail(function (xhr, textStatus, errorThrown) {
        restartSession(term)
    });

    return snapUrl;
}

function messageStyle(style) {
    return {
        finalize: function (div) {
            div.addClass(style);
        }
    }
}


function layoutCompletions(candidates, widthInChars) {
    var max = 0;
    for (var i = 0; i < candidates.length; i++) {
        max = Math.max(max, candidates[i].length);
    }
    max += 2;
    var n = Math.floor(widthInChars / max);
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

function echoCompletionCandidates(term, candidates) {
    term.echo(term.get_prompt() + term.get_command());
    term.echo(layoutCompletions(candidates, term.width() / 8));
}

function handleTerminalCommand(log, term) {
    if (log.type == "CONTROL") {
        switch (log.message) {
            case "CLEAR_SCREEN":
                term.clear();
                term.echo(session.welcomeMessage);
                term.echo(' ');
                break;
        }
        return true;
    }
    return false;
}

function handleTerminalMessage(log, term) {
    if (log.type != "CONTROL") {
        var style = log.type == "ERROR" ? "terminal-message-error" : "terminal-message-success";
        term.echo(log.message, messageStyle(style))
        return log.type == "ERROR";
    }
    return false;
}


$(document).ready(function () {
    jQuery(function ($, undefined) {
        createNewSession(getParam("expression"), getParam("snap"));
        $('#terminal').terminal(function (command, term) {

            if (command == ":snap") {
                var snapUri = makeSnap(term);
                term.echo("Created terminal snapshot [[!;;]" + snapUri + "]", messageStyle("terminal-message-success"));
                return;
            }

            var expression = readExpressionLine(command, term);

            if (expression) {
                $.ajax({
                    type: 'POST',
                    async: false,
                    url: '/execute',
                    data: {id: session.clientId, expression: expression}
                }).done(function (data) {
                    var hadError = false;
                    for (var i = 0; i < data.logs.length; i++) {
                        var log = data.logs[i];
                        if (!handleTerminalCommand(log, term)) {
                            hadError = handleTerminalMessage(log, term) || hadError;
                        }
                    }

                    if (!hadError) {
                        _gaq.push(["_trackEvent", "console", "evaluation", "success"]);
                    } else {
                        _gaq.push(["_trackEvent", "console", "evaluation", "error"]);
                    }

                    session.requesting = false;
                }).fail(function (xhr, textStatus, errorThrown) {
                    restartSession(term)
                });
            } else {
                term.echo(" ");
                session.requesting = false;
            }
        }, {
            greetings: null,
            name: 'js_demo',
            prompt: '[[;white;black]java> ]',
            onInit: function (term) {
                greetings(term);
            },

            keydown: function (event, term) {
                if (event.keyCode == 9) //Tab
                {

                    var completionResult = [];
                    $.ajax({
                        type: 'GET',
                        async: false,
                        cache: false,
                        url: '/completions',
                        data: {id: session.clientId, expression: term.get_command()}
                    })
                        .done(function (data) {
                            completionResult = data;
                        });


                    var candidates = _.map(completionResult.candidates, function (cand) {
                        return cand.value;
                    });
                    var candidatesForms = _.map(completionResult.candidates, function (cand) {
                        return cand.forms;
                    });
                    var promptText = term.get_command();

                    if (candidates.length == 0) {
                        term.set_command(promptText);
                        return false;
                    }

                    if (candidates.length == 1) {
                        var uniqueForms = _.filter(_.unique(candidatesForms[0]), function (form) {
                            return form != candidates[0]
                        });
                        var text = term.get_command().substr(0, parseInt(completionResult.position)) + candidates[0];

                        term.set_command(text);

                        if (uniqueForms.length > 0) {
                            echoCompletionCandidates(term, candidatesForms[0]);
                        }

                        return false;
                    }

                    echoCompletionCandidates(term, candidates);

                    for (var i = candidates[0].length; i > 0; --i) {
                        var prefixedCandidatesCount = _.filter(candidates, function (cand) {
                            return i > cand.length ? false : cand.substr(0, i) == candidates[0].substr(0, i);
                        }).length;

                        if (prefixedCandidatesCount == candidates.length) {
                            term.set_command(promptText.substr(0, parseInt(completionResult.position)) + candidates[0].substr(0, i));
                            return false;
                        }
                    }

                    term.set_command(promptText);

                    return false;
                }
            }

        });
    });
});
