package com.example.digcompsys.service;

import com.example.digcompsys.dto.request.CreateComplaintRequest;
import com.example.digcompsys.dto.request.UpdateStatusRequest;
import com.example.digcompsys.dto.response.ComplaintResponse;
import com.example.digcompsys.model.Complaint;

import java.util.List;

public interface ComplaintService {

    ComplaintResponse createComplaint(CreateComplaintRequest request);

    ComplaintResponse updateStatus(UpdateStatusRequest request);

    ComplaintResponse getComplaintById(Long complaintId);

    List<ComplaintResponse> getAllComplaints();

    ComplaintResponse mapToResponse(Complaint complaint);
}