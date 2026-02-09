package com.example.digcompsys.repository;

import com.example.digcompsys.model.Team;
import com.example.digcompsys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository
        extends JpaRepository<Team, Long> {
    List<Team> findByTeamName(String teamName);

    Optional<Team> findByUsers(User user);
}
