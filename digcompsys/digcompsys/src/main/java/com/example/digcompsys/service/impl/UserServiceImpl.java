package com.example.digcompsys.service.impl;

import com.example.digcompsys.dto.request.CreateUserRequest;
import com.example.digcompsys.dto.request.UpdateUserRequest;
import com.example.digcompsys.dto.response.UserResponse;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // ✅ ADD THIS

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword())) // 🔥 FIX
                .email(request.getEmail())
                .phone(request.getPhone())
                .roleName(request.getRoleName())
                .build();

        userRepository.save(user);

        return mapToResponse(user);
    }

    @Override
    public UserResponse updateUser(Long userId, UpdateUserRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(request.getUserName());

        // 🔥 encode password ONLY if updated
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user.setPhone(request.getPhone());

        userRepository.save(user);

        return mapToResponse(user);
    }

    @Override
    public UserResponse getUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roleName(user.getRoleName())
                .createdAt(user.getCreatedAt())
                .build();
    }
}