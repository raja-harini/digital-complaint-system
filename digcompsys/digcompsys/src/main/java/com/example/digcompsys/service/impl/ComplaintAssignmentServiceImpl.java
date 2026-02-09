package com.example.digcompsys.service.impl;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.ComplaintAssignment;
import com.example.digcompsys.model.Team;
import com.example.digcompsys.repository.ComplaintAssignmentRepository;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.TeamRepository;
import com.example.digcompsys.service.ComplaintAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ComplaintAssignmentServiceImpl implements ComplaintAssignmentService {

    private final ComplaintRepository complaintRepository;
    private final TeamRepository teamRepository;
    private final ComplaintAssignmentRepository complaintAssignmentRepository;

    @Override
    public ComplaintAssignment reassign(Long assignmentId, Long teamId) {

        ComplaintAssignment assignment = complaintAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        assignment.setTeam(team);
        assignment.setStatus("REASSIGNED");
        assignment.setCreatedAt(LocalDateTime.now());

        return complaintAssignmentRepository.save(assignment);
    }

    @Override
    public ComplaintAssignment getByComplaintId(Long complaintId) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        return complaintAssignmentRepository.findByComplaint(complaint)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
    }
}