package com.example.digcompsys.controller;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Team;
import com.example.digcompsys.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/teams")
    public Team create(@RequestBody Team team) {
        return teamService.create(team);
    }

    @GetMapping("/teams/{id}")
    public Team get(@PathVariable Long id) {
        return teamService.getById(id);
    }

    @GetMapping("/teams")
    public List<Team> getAll() {
        return teamService.getAll();
    }

    @GetMapping("/teams/{id}/complaints")
    public List<Complaint> getTeamComplaints(@PathVariable Long id) {
        return teamService.getTeamComplaints(id);
    }

    @PostMapping("/teams/auto-create")
    public Team createTeamAutomatically(
            @RequestParam String teamName,
            @RequestParam String contact
    ) {
        return teamService.createTeamWithAutoAssignedEmployees(teamName, contact);
    }

    @PostMapping("/admin/teams/auto-create")
    public ResponseEntity<Team> autoCreateTeam(
            @RequestParam String teamName,
            @RequestParam String contact
    ) {
        return ResponseEntity.ok(
                teamService.createTeamWithAutoAssignedEmployees(teamName, contact)
        );
    }
}
