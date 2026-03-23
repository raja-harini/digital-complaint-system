package com.example.digcompsys.repository;

import com.example.digcompsys.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {

    List<StatusHistory> findByComplaintComplaintId(Long complaintId);

    List<StatusHistory> findByComplaintComplaintIdOrderByCreatedAtDesc(Long complaintId);

    List<StatusHistory> findByComplaintComplaintIdOrderByHistoryIdAsc(Long complaintId);

    List<StatusHistory> findByUserUserId(Long userId);

    List<StatusHistory> findByTeamTeamId(Long teamId);

    List<StatusHistory> findByActiveFlag(Boolean activeFlag);
}