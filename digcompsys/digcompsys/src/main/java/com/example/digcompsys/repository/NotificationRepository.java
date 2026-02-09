package com.example.digcompsys.repository;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Notification;
import com.example.digcompsys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUser(User user);

    List<Notification> findByNotificationType(String notificationType);

    List<Notification> findByUserId(Long userId);
}
