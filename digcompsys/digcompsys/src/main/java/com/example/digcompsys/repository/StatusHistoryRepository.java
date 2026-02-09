package com.example.digcompsys.repository;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusHistoryRepository
        extends JpaRepository<StatusHistory, Long> {
    List<StatusHistory> findByComplaint(Complaint complaint);

    List<StatusHistory> findByNewStatus(String newStatus);

    List<StatusHistory> findByActiveFlag(Boolean activeFlag);
}
