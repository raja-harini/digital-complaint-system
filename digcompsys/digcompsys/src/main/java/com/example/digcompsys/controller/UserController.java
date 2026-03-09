package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.CreateUserRequest;
import com.example.digcompsys.dto.request.UpdateUserRequest;
import com.example.digcompsys.dto.response.UserResponse;
import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Notification;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.NotificationRepository;
import com.example.digcompsys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ComplaintRepository complaintRepository;
    private final NotificationRepository notificationRepository;

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/complaints")
    public List<Complaint> getUserComplaints(@PathVariable Long id) {
        return complaintRepository.findByUserUserId(id);
    }

//    @GetMapping("/{id}/notifications")
//    public List<Notification> getUserNotifications(@PathVariable Long id) {
//        return notificationRepository.findByUserUserId(id);
//    }
}