document.write('<div id="console" class="console"></div>')

function removeClient(clientId) {
    $.ajax({type: 'POST', async: false, url: '/remove', data: 'id=' + clientId});
}

var requesting = false;
var clientId = null;

$(window).bind('beforeunload', function () {
    removeClient(clientId);
});

$(document).ready(function () {
    var console = $('<div>');
    $('#console').append(console);

    $.post('/create', function (data) {
        clientId = data.id;
    });

    var controller = console.console({
        promptLabel: 'java> ',
        commandValidate: function (line) {
            return !requesting;

        },
        commandHandle: function (line, report) {
            $.post('/execute', {id: clientId, expression: line})
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

            return [];

        },
        welcomeMessage: "Welcome to Java REPL",
        autofocus: true,
        animateScroll: true,
        promptHistory: true,
        charInsertTrigger: function (keycode, line) {
            return true;
        }
    });
});