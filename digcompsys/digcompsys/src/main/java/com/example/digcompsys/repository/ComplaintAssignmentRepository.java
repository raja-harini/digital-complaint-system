package com.example.digcompsys.repository;

import com.example.digcompsys.model.ComplaintAssignment;
import com.example.digcompsys.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintAssignmentRepository extends JpaRepository<ComplaintAssignment, Long> {

    List<ComplaintAssignment> findByComplaintComplaintId(Long complaintId);

    List<ComplaintAssignment> findByTeamTeamId(Long teamId);

    List<ComplaintAssignment> findByUserUserId(Long userId);

    List<ComplaintAssignment> findByStatus(Status status);
}