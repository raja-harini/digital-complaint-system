package com.example.digcompsys.service;

import com.example.digcompsys.model.Complaint;
import java.util.List;

public interface ComplaintService {

    Complaint createComplaint(Complaint complaint);

    Complaint getById(Long id);

    List<Complaint> getAll();

    List<Complaint> getComplaintsByUserEmail(String email);

    List<Complaint> getComplaintsByUserId(Long userId);

    List<Complaint> myComplaints(String email);

    Complaint updateStatus(Long id, String status);

    void escalate(Long id);

    void resolve(Long id);

    void delete(Long id);

    void assignComplaint(Long complaintId, Long teamId);
}
