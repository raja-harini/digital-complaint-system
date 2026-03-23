package com.example.digcompsys.service.impl;

import com.example.digcompsys.model.Category;
import com.example.digcompsys.model.EmployeeDetails;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.EmployeeDetailsRepository;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.service.EmployeeDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final EmployeeDetailsRepository repo;
    private final UserRepository userRepository;

    @Override
    public void saveDetails(Category field, String role, MultipartFile file) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        try {
            EmployeeDetails details = EmployeeDetails.builder()
                    .user(user)
                    .fieldOfWork(field)
                    .preferredRole(role)
                    .document(file.getBytes())
                    .fileName(file.getOriginalFilename())
                    .build();

            repo.save(details);

        } catch (Exception e) {
            throw new RuntimeException("File error");
        }
    }

    @Override
    public EmployeeDetails getMyDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return repo.findByUserUserId(user.getUserId()).orElse(null);
    }

    @Override
    public List<EmployeeDetails> getAll() {
        return repo.findAll();
    }

    @Override
    public EmployeeDetails getByUserId(Long userId) {
        return repo.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Employee details not found"));
    }
}