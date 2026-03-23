package com.example.digcompsys.service.impl;

import com.example.digcompsys.dto.request.CreateComplaintRequest;
import com.example.digcompsys.dto.request.UpdateStatusRequest;
import com.example.digcompsys.dto.response.ComplaintResponse;
import com.example.digcompsys.model.*;
import com.example.digcompsys.repository.*;
import com.example.digcompsys.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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

        System.out.println("REQUEST: " + request);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setPriority(request.getPriority());
        complaint.setStatus(Status.RAISED);
        complaint.setUser(user);

        complaintRepository.save(complaint);

        StatusHistory history = StatusHistory.builder()
                .complaint(complaint)
                .user(user)
                .oldStatus(Status.RAISED)
                .newStatus(Status.RAISED)
                .build();

        statusHistoryRepository.save(history);

        Notification notification = Notification.builder()
                .user(user)
                .complaint(complaint)
                .notificationType(NotificationType.COMPLAINT_SUBMITTED)
                .message("Complaint raised successfully")
                .readFlag(false)
                .build();

        notificationRepository.save(notification);

        return mapToResponse(complaint);
    }

    @Override
    public ComplaintResponse updateStatus(UpdateStatusRequest request) {

        Complaint complaint = complaintRepository.findById(request.getComplaintId())
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User employee = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Status oldStatus = complaint.getStatus();

        complaint.setStatus(request.getNewStatus());
        complaintRepository.save(complaint);

        StatusHistory history = StatusHistory.builder()
                .complaint(complaint)
                .user(employee)
                .oldStatus(oldStatus)
                .newStatus(request.getNewStatus())
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

        return complaintRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ComplaintResponse mapToResponse(Complaint complaint) {

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