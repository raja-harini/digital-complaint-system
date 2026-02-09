package com.example.digcompsys.service.impl;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Team;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.TeamRepository;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    @Override
    public Team create(Team team) {

        if (team.getTeamName() == null || team.getTeamName().isEmpty()) {
            throw new IllegalArgumentException("Team name required");
        }

        return teamRepository.save(team);
    }

    @Override
    public Team getById(Long id) {

        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Complaint> getTeamComplaints(Long teamId) {
        return complaintRepository.findByTeamTeamId(teamId);
    }

    @Override
    @Transactional
    public Team createTeamWithAutoAssignedEmployees(String teamName, String contact) {

        int page = 0;
        int size = 2;

        Page<User> employeesPage =
                userRepository.findByRoleName(
                        User.Role.ROLE_EMPLOYEE,
                        PageRequest.of(page, size)
                );

        List<User> employees = employeesPage.getContent();

        log.info("Employees fetched from DB: {}", employees.size());

        int required = 2;

        if (employees.size() < required) {
            throw new IllegalStateException(
                    "Required: " + required + ", Available: " + employees.size()
            );
        }

        Team team = new Team();
        team.setTeamName(teamName);
        team.setContact(contact);

        teamRepository.save(team);

        for (User user : employees) {
            user.setTeam(team);
        }

        team.setUsers(employees);

        return team;
    }
}
