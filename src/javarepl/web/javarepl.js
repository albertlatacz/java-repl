document.write('<div id="console" class="console"></div>')

function removeClient(clientId) {
    $.ajax({type: 'POST', async: false, url: '/remove', data: 'id=' + clientId});
}

function readExpressionLine(clientId, line) {
    var expression = null;

    $.ajax({type: 'POST', async: false, url: '/readExpression', data: {id: clientId, line: line}})
        .done(function (data) {
            expression = data.expression;
        });

    return expression;
}

var requesting = false;
var clientId = null;
var welcomeMessage = null;

$(window).bind('beforeunload', function () {
    removeClient(clientId);
});

$(document).ready(function () {
    var console = $('<div>');
    $('#console').append(console);

    $.ajax({type: 'POST', async: false, url: '/create'})
        .done(function (data) {
            clientId = data.id;
            welcomeMessage = data.welcomeMessage
        });

    var controller = console.console({
        promptLabel: 'java> ',
        commandValidate: function (line) {
            return !requesting;
        },
        commandHandle: function (line, report) {
            var expression = readExpressionLine(clientId, line);

            if (expression) {
                $.post('/execute', {id: clientId, expression: expression})
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
                        requesting = false;
                    })
                    .fail(function (xhr, textStatus, errorThrown) {
                        report([
                            {msg: "Service timeout. Starting new session...", className: "jquery-console-message-service-error"}
                        ]);

                        removeClient(clientId);
                        $.post('/create', function (data) {
                            clientId = data.id;
                            requesting = false;
                        });
                    });
            } else {
                report([]);
                requesting = false;
            }

            return [];

        },
        completeHandle: function (prefix) {
            var completionResult;
            $.ajax({type: 'GET', async: false, cache: false, url: '/completions', data: {id: clientId, expression: prefix}})
                .done(function (data) {
                    completionResult = data;
                });

            var candidates = completionResult.candidates;
            var promptText = controller.promptText();

            if (candidates.length == 0) {
                return [];
            }

            if (candidates.length == 1) {
                controller.promptText(controller.promptText().substr(0, parseInt(completionResult.position)) + completionResult.candidates[0]);
                return [];
            }

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
            controller.commandResult(buffer, "jquery-console-message-completion");

            for (i = candidates[0].length; i > 0; --i) {
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
        welcomeMessage: welcomeMessage,
        autofocus: true,
        animateScroll: true,
        promptHistory: true,
        charInsertTrigger: function (keycode, line) {
            return true;
        }
    });
});