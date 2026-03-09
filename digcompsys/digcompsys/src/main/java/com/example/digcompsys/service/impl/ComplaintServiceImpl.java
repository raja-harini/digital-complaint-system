package com.example.digcompsys.service.impl;

import com.example.digcompsys.dto.request.CreateComplaintRequest;
import com.example.digcompsys.dto.request.UpdateStatusRequest;
import com.example.digcompsys.dto.response.ComplaintResponse;
import com.example.digcompsys.model.*;
import com.example.digcompsys.repository.*;
import com.example.digcompsys.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final StatusHistoryRepository statusHistoryRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public ComplaintResponse createComplaint(CreateComplaintRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Complaint complaint = Complaint.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .priority(request.getPriority())
                .status(Status.RAISED)
                .user(user)
                .build();

        complaintRepository.save(complaint);

        StatusHistory history = StatusHistory.builder()
                .complaint(complaint)
                .user(user)
                .oldStatus(null)
                .newStatus(Status.RAISED)
                .build();

        statusHistoryRepository.save(history);

        Notification notification = Notification.builder()
                .user(user)
                .complaint(complaint)
                .notificationType(NotificationType.COMPLAINT_SUBMITTED)
                .message("Complaint raised successfully")
                .build();

        notificationRepository.save(notification);

        return mapToResponse(complaint);
    }

    @Override
    public ComplaintResponse updateStatus(UpdateStatusRequest request) {

        Complaint complaint = complaintRepository.findById(request.getComplaintId())
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Status oldStatus = complaint.getStatus();

        complaint.setStatus(request.getNewStatus());
        complaintRepository.save(complaint);

        StatusHistory history = StatusHistory.builder()
                .complaint(complaint)
                .user(user)
                .oldStatus(oldStatus)
                .newStatus(request.getNewStatus())
                .escalationTime(request.getNewStatus() == Status.ESCALATED ? LocalDateTime.now() : null)
                .resolutionTime(request.getNewStatus() == Status.RESOLVED ? LocalDateTime.now() : null)
                .build();

        statusHistoryRepository.save(history);

        Notification notification = Notification.builder()
                .user(complaint.getUser())
                .complaint(complaint)
                .notificationType(NotificationType.COMPLAINT_UPDATED)
                .message("Complaint status updated to " + request.getNewStatus())
                .build();

        notificationRepository.save(notification);

        return mapToResponse(complaint);
    }

    @Override
    public ComplaintResponse getComplaintById(Long complaintId) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        return mapToResponse(complaint);
    }

    @Override
    public List<ComplaintResponse> getAllComplaints() {

        return complaintRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ComplaintResponse mapToResponse(Complaint complaint) {

        return ComplaintResponse.builder()
                .complaintId(complaint.getComplaintId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .category(complaint.getCategory())
                .priority(complaint.getPriority())
                .status(complaint.getStatus())
                .createdAt(complaint.getCreatedAt())
                .userId(complaint.getUser().getUserId())
                .build();
    }
}