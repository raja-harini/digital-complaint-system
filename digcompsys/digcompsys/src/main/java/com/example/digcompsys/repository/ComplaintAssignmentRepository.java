package com.example.digcompsys.repository;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.ComplaintAssignment;
import com.example.digcompsys.model.Team;
import com.example.digcompsys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComplaintAssignmentRepository
        extends JpaRepository<ComplaintAssignment, Long> {
    List<ComplaintAssignment> findByStatus(String status);

    List<ComplaintAssignment> findByUser(User user);

    List<ComplaintAssignment> findByTeam(Team team);

    Optional<ComplaintAssignment> findByComplaint(Complaint complaint);
}
