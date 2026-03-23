package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.AssignComplaintRequest;
import com.example.digcompsys.dto.request.ReassignRequest;
import com.example.digcompsys.model.ComplaintAssignment;
import com.example.digcompsys.repository.ComplaintAssignmentRepository;
import com.example.digcompsys.service.ComplaintAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ComplaintAssignmentController {

    private final ComplaintAssignmentService assignmentService;
    private final ComplaintAssignmentRepository assignmentRepository;

    @PostMapping("/complaints/{id}/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ComplaintAssignment assignComplaint(@PathVariable Long id,
                                               @RequestBody AssignComplaintRequest request) {
        request.setComplaintId(id);
        return assignmentService.assignComplaint(request);
    }

    @PutMapping("/assignments/{id}")
    public ComplaintAssignment reassignComplaint(@PathVariable Long id, @RequestBody ReassignRequest request) {
        request.setAssignmentId(id);
        return assignmentService.reassignComplaint(request);
    }

    @GetMapping("/assignments/complaint/{complaintId}")
    public List<ComplaintAssignment> getAssignmentsByComplaint(@PathVariable Long complaintId) {
        return assignmentRepository.findByComplaintComplaintId(complaintId);
    }
}