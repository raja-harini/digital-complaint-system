package com.example.digcompsys.controller;

import com.example.digcompsys.model.ComplaintAssignment;
import com.example.digcompsys.service.ComplaintAssignmentService;
import com.example.digcompsys.dto.request.AssignmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class ComplaintAssignmentController {

    @Autowired
    private ComplaintAssignmentService complaintAssignmentService;

    // Reassign using path variable + request param
    @PutMapping("/{id}")
    public ComplaintAssignment reassign(
            @PathVariable Long id,
            @RequestParam Long teamId
    ) {
        return complaintAssignmentService.reassign(id, teamId);
    }

    // Reassign using request body
    @PutMapping("/reassign")
    public ComplaintAssignment reassign(
            @RequestBody AssignmentRequest request
    ) {
        return complaintAssignmentService.reassign(
                request.getAssignmentId(),
                request.getTeamId()
        );
    }

    @GetMapping("/complaint/{complaintId}")
    public ComplaintAssignment getByComplaint(
            @PathVariable Long complaintId
    ) {
        return complaintAssignmentService.getByComplaintId(complaintId);
    }
}
