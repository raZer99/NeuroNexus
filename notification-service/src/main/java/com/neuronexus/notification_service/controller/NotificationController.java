package com.neuronexus.notification_service.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notify")
public class NotificationController {

    @PostMapping
    public String notifyTeam(@RequestBody String message){
        System.out.println("Notification Service");
        System.out.println("Message: " + message);
        return "Notification sent successfully";
    }
}
