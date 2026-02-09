package com.example.digcompsys.service.impl;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.ComplaintAssignment;
import com.example.digcompsys.model.Team;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.ComplaintAssignmentRepository;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.TeamRepository;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final TeamRepository teamRepository;
    private final ComplaintAssignmentRepository complaintAssignmentRepository;
    private final UserRepository userRepository;

    @Override
    public Complaint createComplaint(Complaint complaint) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        complaint.setUser(user);
        complaintRepository.save(complaint);

        complaint.setStatus("OPEN");

        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint getById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    @Override
    public List<Complaint> getAll() {
        return complaintRepository.findAll();
    }

    @Override
    public List<Complaint> getComplaintsByUserEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return complaintRepository.findByUser(user);
    }

    @Override
    public List<Complaint> getComplaintsByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return complaintRepository.findByUser(user);
    }

    @Override
    public List<Complaint> myComplaints(String email) {
        return getComplaintsByUserEmail(email);
    }

    @Override
    public Complaint updateStatus(Long id, String status) {

        Complaint complaint = getById(id);
        complaint.setStatus(status);

        return complaintRepository.save(complaint);
    }

    @Override
    public void escalate(Long id) {

        Complaint complaint = getById(id);
        complaint.setStatus("ESCALATED");

        complaintRepository.save(complaint);
    }

    @Override
    public void resolve(Long id) {

        Complaint complaint = getById(id);
        complaint.setStatus("RESOLVED");

        complaintRepository.save(complaint);
    }

    @Override
    public void delete(Long id) {

        Complaint complaint = getById(id);
        complaintRepository.delete(complaint);
    }

    @Override
    public void assignComplaint(Long complaintId, Long teamId) {

        Complaint complaint = getById(complaintId);

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        ComplaintAssignment assignment = new ComplaintAssignment();
        assignment.setComplaint(complaint);
        assignment.setTeam(team);
        assignment.setStatus("ASSIGNED");
        assignment.setCreatedAt(LocalDateTime.now());

        complaintAssignmentRepository.save(assignment);

        complaint.setStatus("ASSIGNED");
        complaintRepository.save(complaint);
    }
}
