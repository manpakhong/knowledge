<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
	<head>
	    <title>Chat WebSocket</title>
	    <script src="<c:url value="/resources/scripts/jquery-3.4.1.js" />"></script>
		<script src="<c:url value="/resources/scripts/sockjs-0.3.4.min.js" />"></script>
		<script src="<c:url value="/resources/scripts/stomp.js" />"></script>
	    <script src="<c:url value="/resources/scripts/webSocketSendToUserApp.js" />"></script>
	    
	    
	    <script type="text/javascript">
	    

	        



	        
	    </script>
	    
	</head>
	
	<body onload="disconnect()">

		<div>
		
		
			<div>
				<input type="text" id="from" placeholder="Choose a nickname"/>
			</div>
			<br />
			<div>
				<input type="text" id="to" placeholder="Choose a nickname"/>
			</div>
		    <div>
		        <button id="connect" onclick="connect();">Connect</button>
		        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
		    </div>
		    <br />
		    <div id="conversationDiv">
		        <input type="text" id="text" placeholder="Write a message..."/>
		        <button id="sendMessage" onclick="sendMessage();">Send</button>
		        <p id="response"></p>
		    </div>
		</div>

	</body>
</html>