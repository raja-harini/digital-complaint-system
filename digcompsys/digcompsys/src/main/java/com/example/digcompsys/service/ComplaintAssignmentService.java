package com.example.digcompsys.service;

import com.example.digcompsys.model.ComplaintAssignment;

public interface ComplaintAssignmentService {

    ComplaintAssignment reassign(Long assignmentId, Long teamId);

    ComplaintAssignment getByComplaintId(Long complaintId);
}