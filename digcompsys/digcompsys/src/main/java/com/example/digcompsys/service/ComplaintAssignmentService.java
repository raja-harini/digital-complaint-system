package com.example.digcompsys.service;

import com.example.digcompsys.dto.request.AssignComplaintRequest;
import com.example.digcompsys.dto.request.ReassignRequest;
import com.example.digcompsys.model.ComplaintAssignment;

public interface ComplaintAssignmentService {

    ComplaintAssignment assignComplaint(AssignComplaintRequest request);

    ComplaintAssignment reassignComplaint(ReassignRequest request);
}