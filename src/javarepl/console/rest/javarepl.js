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
        autofocus: true,
        animateScroll: true,
        promptHistory: true,
        charInsertTrigger: function (keycode, line) {
            return true;
        }
    });
});