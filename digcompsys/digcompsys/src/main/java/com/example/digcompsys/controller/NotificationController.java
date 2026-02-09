package com.example.digcompsys.controller;

import com.example.digcompsys.model.Notification;
import com.example.digcompsys.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Notification send(@RequestBody Notification notification){
        return notificationService.send(notification);
    }

    @PutMapping("/{id}/read")
    public String markRead(@PathVariable Long id){
        notificationService.markAsRead(id);
        return "Marked as read";
    }
}