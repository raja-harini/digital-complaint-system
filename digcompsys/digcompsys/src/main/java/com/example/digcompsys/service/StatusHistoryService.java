package com.example.digcompsys.service;

import com.example.digcompsys.model.StatusHistory;

import java.util.List;

public interface StatusHistoryService {

    StatusHistory addHistory(StatusHistory history);

    List<StatusHistory> getByComplaint(Long complaintId);
}