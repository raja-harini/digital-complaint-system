package com.example.digcompsys.controller;

import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.Notification;
import com.example.digcompsys.model.User;
import com.example.digcompsys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ PUBLIC REGISTER API
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.createUser(user);
    }

    // 🔒 PROTECTED APIs
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User deleted";
    }

    @GetMapping("/{id}/complaints")
    public List<Complaint> getUserComplaints(@PathVariable Long id){
        return userService.getUserComplaints(id);
    }

    @GetMapping("/{id}/notifications")
    public List<Notification> getUserNotifications(@PathVariable Long id){
        return userService.getNotifications(id);
    }
}