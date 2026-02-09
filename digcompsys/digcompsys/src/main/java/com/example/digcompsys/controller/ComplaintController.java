package com.example.digcompsys.controller;

import com.example.digcompsys.dto.response.ComplaintResponse;
import com.example.digcompsys.model.Complaint;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;

    @PostMapping
    public ResponseEntity<ComplaintResponse> createComplaint(@RequestBody Complaint complaint) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        complaint.setUser(user);

        Complaint saved = complaintService.createComplaint(complaint);

        return ResponseEntity.status(201).body(mapToDto(saved));
    }

    @GetMapping("/my")
    public List<ComplaintResponse> getMyComplaints(Authentication auth) {

        User user = userRepository.findByEmail(auth.getName()).orElseThrow();

        return complaintRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @GetMapping("/id/{id}")
    public ComplaintResponse getById(@PathVariable Long id) {
        Complaint c = complaintService.getById(id);
        return mapToDto(c);
    }

    @GetMapping
    public List<ComplaintResponse> getAll() {
        return complaintService.getAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @PutMapping("/id/{id}/status")
    public Complaint updateStatus(@PathVariable Long id,
                                  @RequestParam String status) {
        return complaintService.updateStatus(id, status);
    }

    @PutMapping("/id/{id}/escalate")
    public String escalate(@PathVariable Long id) {
        complaintService.escalate(id);
        return "Complaint escalated";
    }

    @PutMapping("/id/{id}/resolve")
    public String resolve(@PathVariable Long id) {
        complaintService.resolve(id);
        return "Complaint resolved";
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable Long id) {
        complaintService.delete(id);
    }

    @PostMapping("/id/{id}/assign")
    public String assign(@PathVariable Long id,
                         @RequestParam Long teamId) {
        complaintService.assignComplaint(id, teamId);
        return "Assigned";
    }

    private ComplaintResponse mapToDto(Complaint c) {

        ComplaintResponse dto = new ComplaintResponse();
        dto.setId(c.getId());
        dto.setTitle(c.getTitle());
        dto.setDescription(c.getDescription());
        dto.setCategory(c.getCategory());
        dto.setPriority(c.getPriority());
        dto.setStatus(c.getStatus());

        return dto;
    }
}
