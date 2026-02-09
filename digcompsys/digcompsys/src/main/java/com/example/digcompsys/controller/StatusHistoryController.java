package com.example.digcompsys.controller;

import com.example.digcompsys.model.StatusHistory;
import com.example.digcompsys.service.StatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class StatusHistoryController {
    @Autowired
    private StatusHistoryService statusHistoryService;

    @PostMapping
    public StatusHistory add(@RequestBody StatusHistory history){
        return statusHistoryService.addHistory(history);
    }

    @GetMapping("/complaints/{id}/history")
    public List<StatusHistory> getHistory(@PathVariable Long id){
        return statusHistoryService.getByComplaint(id);
    }
}