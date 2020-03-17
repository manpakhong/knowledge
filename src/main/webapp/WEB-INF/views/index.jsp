<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>SockJS Tester</title>

    
	<script src="<c:url value="/resources/scripts/jquery-3.4.1.js" />"></script>
	<script src="<c:url value="/resources/scripts/sockjs-0.3.4.min.js" />"></script>
    <script language="javascript" type="text/javascript">
        var ping;
        var sockjs;

        jQuery(function ($) {
            function writePing(message) {
                $('#pingOutput').append(message + '\n');
            }

            function writeStatus(message) {
                $("#statusOutput").val($("#statusOutput").val() + message + '\n');
            }

            function writeMessage(message) {
                $('#messageOutput').append(message + '\n')
            }

            $('#connect')
                    .click(function doConnect() {
                        sockjs = new SockJS($("#target").val());

                        sockjs.onopen = function (evt) {
                            writeStatus("CONNECTED");

                            var ping = setInterval(function () {
                                if (sockjs != "undefined") {
                                    sockjs.send("ping");
                                }
                            }, 3000);
                        };

                        sockjs.onclose = function (evt) {
                            writeStatus("DISCONNECTED");
                        };

                        sockjs.onmessage = function (evt) {
                            if (evt.data === "ping") {
                                writePing(evt.data);
                            } else {
                                writeMessage('ECHO: ' + evt.data);
                            }
                        };

                        sockjs.onerror = function (evt) {
                            onError(writeStatus('ERROR:' + evt.data))
                        };
                    });

            $('#disconnect')
                    .click(function () {
                        if(typeof sockjs != 'undefined') {
                            sockjs.close();
                            sockjs = undefined;
                        } else {
                            alert("Not connected.");
                        }
                    });

            $('#send')
                    .click(function () {
                        if(typeof sockjs != 'undefined') {
                            sockjs.send($('#message').val());
                        } else {
                            alert("Not connected.");
                        }
                    });
        });
    </script>
</head>

<body>
<h2>SockJS Tester</h2>
    Target:
    <input id="target" size="40" value="http://localhost:8080/knowledge/echoHandler"/>
    <br/>
    <button id="connect">Connect</button>
    <button id="disconnect">Disconnect</button>
    <br/>
    <br/>Message:
    <input id="message" value=""/>
    <button id="send">Send</button>
    <br/>
    <p>Status output:</p>
    <pre><textarea id="statusOutput" rows="10" cols="50"></textarea></pre>
    <p>Message output:</p>
    <pre><textarea id="messageOutput" rows="10" cols="50"></textarea></pre>
    <p>Ping output:</p>
    <pre><textarea id="pingOutput" rows="10" cols="50"></textarea></pre>
</body>
</html>
