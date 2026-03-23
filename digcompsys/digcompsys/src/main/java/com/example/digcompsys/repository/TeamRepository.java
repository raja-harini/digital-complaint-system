package com.example.digcompsys.repository;

import com.example.digcompsys.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByTeamLead_UserId(Long userId);

    List<Team> findByEmployees_UserId(Long userId);

    List<Team> findAllByOrderByTeamIdDesc();
}