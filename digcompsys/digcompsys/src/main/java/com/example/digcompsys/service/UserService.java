package com.example.digcompsys.service;

import com.example.digcompsys.dto.request.RegisterRequest;
import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Notification;
import com.example.digcompsys.model.User;

import java.util.List;

public interface UserService {

    User register(RegisterRequest req);

    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    List<Complaint> getUserComplaints(Long userId);

    List<Notification> getNotifications(Long userId);
}