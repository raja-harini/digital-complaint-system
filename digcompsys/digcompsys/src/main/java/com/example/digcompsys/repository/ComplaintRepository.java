package com.example.digcompsys.repository;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByUserUserId(Long userId);

    List<Complaint> findByUserUserIdOrderByCreatedAtDesc(Long userId);

    List<Complaint> findAllByOrderByCreatedAtDesc();

    List<Complaint> findByStatus(Status status);

    List<Complaint> findByAssignment_Team_TeamId(Long teamId);
}