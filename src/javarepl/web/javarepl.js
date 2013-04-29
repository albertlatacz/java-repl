document.write('<div id="console" class="console"></div>')

var requesting = false;
var clientId = null;

$(window).bind('beforeunload', function () {
    $.ajax({
        type: 'POST',
        async: false,
        url: '/remove',
        data: 'id=' + clientId});
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
                    if (xhr.status === 504) {
                        report([
                            {msg: "Session timeout. Starting new session...", className: "jquery-console-message-error"}
                        ]);
                    } else {
                        report([
                            {msg: errorThrown + ". Starting new session...", className: "jquery-console-message-error"}
                        ]);
                    }

                    $.ajax({type: 'POST', async: false, url: '/remove', data: 'id=' + clientId});
                    $.post('/create', function (data) {
                        clientId = data.id;
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