package com.example.digcompsys.repository;

import com.example.digcompsys.model.User;
import com.example.digcompsys.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    Optional<User> findByRoleName(RoleName roleName);
}