package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.CreateTeamRequest;
import com.example.digcompsys.model.ComplaintAssignment;
import com.example.digcompsys.model.Team;
import com.example.digcompsys.repository.ComplaintAssignmentRepository;
import com.example.digcompsys.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TeamController {

    private final TeamService teamService;
    private final ComplaintAssignmentRepository assignmentRepository;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Team createTeam(@RequestBody CreateTeamRequest request) {
        return teamService.createTeam(request);
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("WORKING");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Team getTeam(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}/complaints")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ComplaintAssignment> getTeamComplaints(@PathVariable Long id) {
        return assignmentRepository.findByTeamTeamId(id);
    }
}