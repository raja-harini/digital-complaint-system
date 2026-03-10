package com.example.digcompsys.scheduler;

import com.example.digcompsys.model.*;
import com.example.digcompsys.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ComplaintSLAScheduler {

    private final ComplaintRepository complaintRepository;
    private final StatusHistoryRepository statusHistoryRepository;
    private final NotificationRepository notificationRepository;

//    @Scheduled(fixedRate = 3600000)
    @Scheduled(fixedRate = 30000)
    public void escalateComplaints() {

        List<Complaint> complaints = complaintRepository.findAll();

        for (Complaint complaint : complaints) {

            if (complaint.getStatus() != Status.RESOLVED &&
                    complaint.getStatus() != Status.ESCALATED &&
//                    complaint.getCreatedAt().isBefore(LocalDateTime.now().minusHours(48))
                   complaint.getCreatedAt().isBefore(LocalDateTime.now().minusMinutes(1))) {

                Status oldStatus = complaint.getStatus();

                complaint.setStatus(Status.ESCALATED);
                complaintRepository.save(complaint);

                StatusHistory history = StatusHistory.builder()
                        .complaint(complaint)
                        .user(complaint.getUser())
                        .oldStatus(oldStatus)
                        .newStatus(Status.ESCALATED)
                        .escalationTime(LocalDateTime.now())
                        .build();

                statusHistoryRepository.save(history);

                Notification notification = Notification.builder()
                        .user(complaint.getUser())
                        .complaint(complaint)
                        .notificationType(NotificationType.COMPLAINT_UPDATED)
                        .message("Complaint automatically escalated due to SLA breach")
                        .build();

                notificationRepository.save(notification);
            }
        }
    }
}