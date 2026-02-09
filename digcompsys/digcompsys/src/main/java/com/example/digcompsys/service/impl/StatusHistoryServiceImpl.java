package com.example.digcompsys.service.impl;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.StatusHistory;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.StatusHistoryRepository;
import com.example.digcompsys.service.StatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;
    private final ComplaintRepository complaintRepository;

    @Override
    public StatusHistory addHistory(StatusHistory history) {

        if (history.getComplaint() == null) {
            throw new RuntimeException("Complaint required");
        }

        history.setEscalationTime(LocalDateTime.now());
        history.setActiveFlag(true);

        return statusHistoryRepository.save(history);
    }

    @Override
    public List<StatusHistory> getByComplaint(Long complaintId) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        return statusHistoryRepository.findByComplaint(complaint);
    }
}