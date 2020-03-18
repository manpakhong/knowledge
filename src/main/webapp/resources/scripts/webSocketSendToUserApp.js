// var ws = null;
//
//function connect() {
//    var socket = new WebSocket('ws://localhost:8080/knowledge/greeting');
//    ws = Stomp.over(socket);
//
//    ws.connect({}, function(frame) {
//        ws.subscribe("/user/queue/errors", function(message) {
//            alert("Error " + message.body);
//        });
//
//        ws.subscribe("/user/queue/reply", function(message) {
//            alert("Message " + message.body);
//        });
//    }, function(error) {
//        alert("STOMP error " + error);
//    });
//}
//
//function disconnect() {
//    if (ws != null) {
//        ws.close();
//    }
//    setConnected(false);
//    console.log("Disconnected");
//}

var stompClient = null;


function sendMessage() {
	
	var from = document.getElementById('from').value;
	var to = document.getElementById('to').value;
    var text = document.getElementById('text').value;
    stompClient.send(
//    		"/" + to + 
    		"/app/greeting", {}, JSON.stringify({'from':from, 'to': to, 'text':text}));
}

function showMessageOutput(messageOutput) {
	
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(messageOutput));
    response.appendChild(p);
}

function setConnected(connected) {
	
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
	
    var socket = new SockJS('/knowledge/greeting');
    stompClient = Stomp.over(socket);  
    
    stompClient.connect({}, function(frame) {
        
    	setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/reply', function(output) {
        	
//            showMessageOutput(JSON.parse(messageOutput.body));
        	showMessageOutput(output.body);
        });
    });
}

function disconnect() {
	
    if(stompClient != null) {
        stompClient.disconnect();
    }
    
    setConnected(false);
    console.log("Disconnected");
}

