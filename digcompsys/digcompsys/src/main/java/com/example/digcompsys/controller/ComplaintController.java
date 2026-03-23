package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.CreateComplaintRequest;
import com.example.digcompsys.dto.request.UpdateStatusRequest;
import com.example.digcompsys.dto.response.ComplaintResponse;
import com.example.digcompsys.model.Status;
import com.example.digcompsys.model.StatusHistory;
import com.example.digcompsys.model.Team;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.ComplaintRepository;
import com.example.digcompsys.repository.StatusHistoryRepository;
import com.example.digcompsys.repository.TeamRepository;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;
    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createComplaint(@RequestBody CreateComplaintRequest request) {
        return ResponseEntity.ok(complaintService.createComplaint(request));
    }

    @GetMapping("/{id}")
    public ComplaintResponse getComplaint(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    @GetMapping
    public List<ComplaintResponse> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public List<ComplaintResponse> getMyComplaints() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return complaintRepository.findByUserUserIdOrderByCreatedAtDesc(user.getUserId())
                .stream()
                .map(complaintService::mapToResponse)
                .toList();
    }

    @PostMapping("/{id}/query")
    @PreAuthorize("hasRole('USER')")
    public String raiseQuery(@PathVariable Long id, @RequestBody Map<String, String> req) {

        String message = req.get("message");

        return "Query submitted";
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ComplaintResponse updateStatus(@PathVariable Long id,
                                          @RequestBody UpdateStatusRequest request) {

        request.setComplaintId(id);
        return complaintService.updateStatus(request);
    }

    @GetMapping("/employee")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<ComplaintResponse> getEmployeeComplaints() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User employee = userRepository.findByEmail(email).orElseThrow();

        List<Team> teams = teamRepository.findByEmployees_UserId(employee.getUserId());

        if (teams.isEmpty()) {
            return List.of();
        }

        Long teamId = teams.get(0).getTeamId();

        return complaintRepository.findByAssignment_Team_TeamId(teamId)
                .stream()
                .filter(c -> c.getAssignment() != null)
                .map(complaint -> ComplaintResponse.builder()
                        .complaintId(complaint.getComplaintId())
                        .title(complaint.getTitle())
                        .description(complaint.getDescription())
                        .category(complaint.getCategory())
                        .priority(complaint.getPriority())
                        .status(complaint.getStatus())
                        .createdAt(complaint.getCreatedAt())
                        .userId(complaint.getUser().getUserId())
                        .build())
                .toList();
    }

    @PutMapping("/{id}/escalate")
    public ComplaintResponse escalateComplaint(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {
        request.setComplaintId(id);
        request.setNewStatus(Status.ESCALATED);
        return complaintService.updateStatus(request);
    }

    @PutMapping("/{id}/resolve")
    public ComplaintResponse resolveComplaint(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {
        request.setComplaintId(id);
        request.setNewStatus(Status.RESOLVED);
        return complaintService.updateStatus(request);
    }

    @GetMapping("/{id}/history")
    @PreAuthorize("hasAnyRole('USER','ADMIN','EMPLOYEE')")
    public List<StatusHistory> getStatusHistory(@PathVariable Long id) {
        return statusHistoryRepository.findByComplaintComplaintIdOrderByHistoryIdAsc(id);
    }

    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        complaintRepository.deleteById(id);
    }
}