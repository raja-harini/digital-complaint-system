package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.CreateTeamRequest;
import com.example.digcompsys.model.ComplaintAssignment;
import com.example.digcompsys.model.Team;
import com.example.digcompsys.repository.ComplaintAssignmentRepository;
import com.example.digcompsys.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final ComplaintAssignmentRepository assignmentRepository;

    @PostMapping
    public Team createTeam(@RequestBody CreateTeamRequest request) {
        return teamService.createTeam(request);
    }

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}/complaints")
    public List<ComplaintAssignment> getTeamComplaints(@PathVariable Long id) {
        return assignmentRepository.findByTeamTeamId(id);
    }
}