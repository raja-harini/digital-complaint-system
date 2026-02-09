package com.example.digcompsys.repository;

import com.example.digcompsys.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByRoleName(User.Role roleName);

    Optional<User> findByUserName(String userName);

    Page<User> findByRoleName(User.Role roleName, Pageable pageable);

}
