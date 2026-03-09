package com.example.digcompsys.controller;

import com.example.digcompsys.model.StatusHistory;
import com.example.digcompsys.repository.StatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatusHistoryController {

    private final StatusHistoryRepository statusHistoryRepository;

    @PostMapping("/history")
    public StatusHistory createHistory(@RequestBody StatusHistory history) {
        return statusHistoryRepository.save(history);
    }

    @GetMapping("/complaints/{id}/history")
    public List<StatusHistory> getComplaintHistory(@PathVariable Long id) {
        return statusHistoryRepository.findByComplaintComplaintId(id);
    }
}