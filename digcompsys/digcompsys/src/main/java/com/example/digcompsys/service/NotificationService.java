package com.example.digcompsys.service;

import com.example.digcompsys.model.Notification;

public interface NotificationService {

    Notification send(Notification notification);

    void markAsRead(Long notificationId);
}