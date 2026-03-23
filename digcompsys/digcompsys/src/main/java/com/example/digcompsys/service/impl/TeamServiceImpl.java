package com.example.digcompsys.service.impl;

import com.example.digcompsys.dto.request.CreateTeamRequest;
import com.example.digcompsys.model.Team;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.TeamRepository;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Override
    public Team createTeam(CreateTeamRequest request) {

        User lead = userRepository.findById(request.getTeamLeadId())
                .orElseThrow(() -> new RuntimeException("Team lead not found"));

        List<User> employees = userRepository.findAllById(request.getEmployeeIds());

        Team team = new Team();
        team.setTeamName(request.getTeamName());
        team.setContact(request.getContact());
        team.setTeamLead(lead);

        Team savedTeam = teamRepository.save(team);

        savedTeam.setEmployees(employees);

        return teamRepository.save(savedTeam);
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAllByOrderByTeamIdDesc();
    }
}