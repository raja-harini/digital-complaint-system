package com.example.digcompsys.repository;

import com.example.digcompsys.model.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

    Optional<EmployeeDetails> findByUserUserId(Long userId);
}