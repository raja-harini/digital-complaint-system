package com.example.digcompsys.service.impl;

import com.example.digcompsys.dto.request.AssignComplaintRequest;
import com.example.digcompsys.dto.request.ReassignRequest;
import com.example.digcompsys.model.*;
import com.example.digcompsys.repository.*;
import com.example.digcompsys.service.ComplaintAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplaintAssignmentServiceImpl implements ComplaintAssignmentService {

    private final ComplaintRepository complaintRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final ComplaintAssignmentRepository assignmentRepository;

    @Override
    public ComplaintAssignment assignComplaint(AssignComplaintRequest request) {

        Complaint complaint = complaintRepository.findById(request.getComplaintId())
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        User admin = userRepository.findById(request.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        ComplaintAssignment assignment = ComplaintAssignment.builder()
                .complaint(complaint)
                .team(team)
                .user(admin)
                .build();

        return assignmentRepository.save(assignment);
    }

    @Override
    public ComplaintAssignment reassignComplaint(ReassignRequest request) {

        ComplaintAssignment assignment = assignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        Team newTeam = teamRepository.findById(request.getNewTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        User admin = userRepository.findById(request.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        assignment.setTeam(newTeam);
        assignment.setUser(admin);

        return assignmentRepository.save(assignment);
    }
}
