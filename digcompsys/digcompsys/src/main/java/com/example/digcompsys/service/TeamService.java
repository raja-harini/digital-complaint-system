package com.example.digcompsys.service;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Team;

import java.util.List;

public interface TeamService {

    Team create(Team team);

    Team getById(Long id);

    List<Team> getAll();

    List<Complaint> getTeamComplaints(Long teamId);

    Team createTeamWithAutoAssignedEmployees(String teamName, String contact);
}
