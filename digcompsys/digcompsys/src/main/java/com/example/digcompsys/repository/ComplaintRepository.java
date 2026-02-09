package com.example.digcompsys.repository;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByStatus(String status);

    List<Complaint> findByPriority(String priority);

    List<Complaint> findByUser(User user);

    List<Complaint> findByCategory(String category);

    List<Complaint> findByTeamTeamId(Long teamId);

    List<Complaint> findByUserId(Long id);
}
