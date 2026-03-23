package com.example.digcompsys.controller;

import com.example.digcompsys.dto.response.EmployeeDetailsResponse;
import com.example.digcompsys.model.Category;
import com.example.digcompsys.model.EmployeeDetails;
import com.example.digcompsys.service.EmployeeDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/employee-details")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeDetailsController {

    private final EmployeeDetailsService service;

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String uploadDetails(
            @RequestParam Category field,
            @RequestParam String role,
            @RequestParam MultipartFile file
    ) {
        service.saveDetails(field, role, file);
        return "Saved";
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public EmployeeDetails getMyDetails() {
        return service.getMyDetails();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmployeeDetailsResponse> getAllForAdmin() {
        return service.getAll()
                .stream()
                .map(ed -> EmployeeDetailsResponse.builder()
                        .userId(ed.getUser().getUserId())
                        .userName(ed.getUser().getUserName())
                        .email(ed.getUser().getEmail())
                        .fieldOfWork(ed.getFieldOfWork().name())
                        .preferredRole(ed.getPreferredRole())
                        .documentUrl("/employee-details/document/" + ed.getUser().getUserId())
                        .build())
                .toList();
    }

    @GetMapping("/document/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ByteArrayResource> getDocument(@PathVariable Long userId) {

        EmployeeDetails ed = service.getByUserId(userId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + ed.getFileName() + "\"")
                .body(new ByteArrayResource(ed.getDocument()));
    }
}