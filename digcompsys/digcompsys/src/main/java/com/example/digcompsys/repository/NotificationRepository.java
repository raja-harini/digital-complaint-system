package com.example.digcompsys.repository;

import com.example.digcompsys.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserUserId(Long userId);

    List<Notification> findByComplaintComplaintId(Long complaintId);

    List<Notification> findByReadFlag(Boolean readFlag);
}