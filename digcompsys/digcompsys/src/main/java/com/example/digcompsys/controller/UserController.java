package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.CreateUserRequest;
import com.example.digcompsys.dto.request.UpdateUserRequest;
import com.example.digcompsys.dto.response.UserResponse;
import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.RoleName;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.NotificationRepository;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ComplaintRepository complaintRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

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

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsersForAdmin() {
        return userRepository.findByRoleName(RoleName.USER)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @GetMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getEmployees() {
        return userRepository.findByRoleName(RoleName.EMPLOYEE)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("userId") Long userId) {
        try {
            String fileName = file.getOriginalFilename();
            String uploadDir = "uploads/";
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            User user = userRepository.findById(userId)
                    .orElseThrow();
            user.setDocumentUrl(path.toString());
            userRepository.save(user);
            return "Uploaded successfully";
        } catch (Exception e) {
            throw new RuntimeException("Upload failed");
        }
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roleName(user.getRoleName())
                .createdAt(user.getCreatedAt())
                .documentUrl(user.getDocumentUrl())
                .build();
    }
}