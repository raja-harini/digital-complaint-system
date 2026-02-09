package com.example.digcompsys.service.impl;

import com.example.digcompsys.model.Notification;
import com.example.digcompsys.repository.NotificationRepository;
import com.example.digcompsys.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification send(Notification notification) {

        notification.setSentAt(LocalDateTime.now());
        notification.setRead(false);

        return notificationRepository.save(notification);
    }

    @Override
    public void markAsRead(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notificationRepository.save(notification);
    }
}