package com.example.digcompsys.service;

import com.example.digcompsys.dto.request.CreateTeamRequest;
import com.example.digcompsys.model.Team;

import java.util.List;

public interface TeamService {

    Team createTeam(CreateTeamRequest request);

    Team getTeamById(Long teamId);

    List<Team> getAllTeams();
}