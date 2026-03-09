package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.NotificationRequest;
import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Notification;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.NotificationRepository;
import com.example.digcompsys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;

    @PostMapping("/notifications")
    public Notification createNotification(@RequestBody NotificationRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Complaint complaint = complaintRepository.findById(request.getComplaintId()).orElseThrow();

        Notification notification = Notification.builder()
                .user(user)
                .complaint(complaint)
                .notificationType(request.getNotificationType())
                .message(request.getMessage())
                .build();

        return notificationRepository.save(notification);
    }

    @GetMapping("/users/{id}/notifications")
    public List<Notification> getUserNotifications(@PathVariable Long id) {
        return notificationRepository.findByUserUserId(id);
    }

    @PutMapping("/notifications/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {

        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.setReadFlag(true);

        return notificationRepository.save(notification);
    }
}