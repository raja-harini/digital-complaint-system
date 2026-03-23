package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.NotificationRequest;
import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Notification;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.NotificationRepository;
import com.example.digcompsys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;

    @PostMapping
    public Notification createNotification(@RequestBody NotificationRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Complaint complaint = complaintRepository.findById(request.getComplaintId()).orElseThrow();

        Notification notification = Notification.builder()
                .user(user)
                .complaint(complaint)
                .notificationType(request.getNotificationType())
                .message(request.getMessage())
                .readFlag(false)
                .build();

        return notificationRepository.save(notification);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Notification> getUserNotifications() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return notificationRepository.findByUserUserIdOrderBySentAtDesc(user.getUserId());
    }

    @GetMapping("/users/{id}")
    public List<Notification> getUserNotificationsById(@PathVariable Long id) {
        return notificationRepository.findByUserUserId(id);
    }

    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {

        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.setReadFlag(true);

        return notificationRepository.save(notification);
    }

    @PutMapping("/{id}/unread")
    public Notification markUnread(@PathVariable Long id) {

        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.setReadFlag(false);

        return notificationRepository.save(notification);
    }

    @PutMapping("/{id}/toggle")
    public Notification toggleNotification(@PathVariable Long id) {

        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        n.setReadFlag(!n.getReadFlag());

        return notificationRepository.save(n);
    }
}