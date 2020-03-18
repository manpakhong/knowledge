package com.rabbitforever.knowledge.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.rabbitforever.knowledge.models.eos.Message;

@Controller
public class WebsocketSendToUserController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    private Gson gson = new Gson();

    @MessageMapping("/greeting")
    @SendToUser("/queue/reply")
    public String processMessageFromClient(@Payload String message, Principal principal) throws Exception {

       Message messageObj =  gson.fromJson(message, Message.class);
       String name = principal.getName();
       String rtnMsg = "I am: " + name + ", to: " + messageObj.getTo();
       messagingTemplate.convertAndSendToUser(messageObj.getTo(), "/queue/reply", rtnMsg);
       

       
       return "orig";
        
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
