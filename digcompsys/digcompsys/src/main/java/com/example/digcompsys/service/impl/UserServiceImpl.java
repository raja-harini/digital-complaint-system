package com.example.digcompsys.service.impl;

import com.example.digcompsys.dto.request.RegisterRequest;
import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Notification;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.NotificationRepository;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;
    private final NotificationRepository notificationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRequest req) {

        User user = new User();
        user.setUserName(req.getUserName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        if (req.getRoleName() == null || req.getRoleName().isEmpty()) {
            throw new IllegalArgumentException("Role is required");
        }

        if (req.getRoleName().equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("Admin registration is not allowed");
        }

        String role = req.getRoleName().toUpperCase();

        if (!role.equals("USER") && !role.equals("EMPLOYEE")) {
            throw new RuntimeException("Invalid role");
        }

        user.setRoleName(User.Role.valueOf("ROLE_" + role));

        return userRepository.save(user);
    }

    @Override
    public User createUser(User user) {

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        if (user.getRoleName() == null) {
            throw new IllegalArgumentException("Role is required");
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {

        User existingUser = getUserById(id);

        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setRoleName(user.getRoleName());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public List<Complaint> getUserComplaints(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return complaintRepository.findByUser(user);
    }

    @Override
    public List<Notification> getNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }
}
