package com.example.digcompsys.service;

import com.example.digcompsys.dto.request.CreateUserRequest;
import com.example.digcompsys.dto.request.UpdateUserRequest;
import com.example.digcompsys.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse updateUser(Long userId, UpdateUserRequest request);

    UserResponse getUserById(Long userId);

    List<UserResponse> getAllUsers();

    void deleteUser(Long userId);
}
