document.write('<div id="console" class="console"></div>')

var requesting = false;

$(document).ready(function () {
    var console = $('<div>');
    $('#console').append(console);

    var controller = console.console({
        promptLabel: 'java> ',
        commandValidate: function (line) {
            return !requesting;

        },
        commandHandle: function (line, report) {
            $.post('/execute', {expression: line})
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

                    report(messages);
                    requesting = false;
                });

            return [];

        },
        completeHandle: function (prefix) {
            var completionResult;
            $.ajax({type: 'GET', async: false, url: '/completions', data: {expression: prefix}})
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

            for (var i = candidates[0].length - 1; i > 0; i--) {
                var prefixedCandidatesCount = _.filter(candidates,function (cand) {
                    return cand.length > i
                        ? cand.substr(0, i) == candidates[0].substr(0, i) :
                        false
                }).length;
                if (candidates.length == prefixedCandidatesCount) {
                    controller.promptText(promptText.substr(0, parseInt(completionResult.position)) + candidates[0].substr(0, i));
                    return [];
                }
            }

            return [];
        },
        autofocus: true,
        animateScroll: true,
        promptHistory: true,
        charInsertTrigger: function (keycode, line) {
            return true;
        }
    });
});