package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.CreateComplaintRequest;
import com.example.digcompsys.dto.request.UpdateStatusRequest;
import com.example.digcompsys.dto.response.ComplaintResponse;
import com.example.digcompsys.model.Status;
import com.example.digcompsys.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ComplaintResponse createComplaint(@RequestBody CreateComplaintRequest request) {
        return complaintService.createComplaint(request);
    }

    @GetMapping("/{id}")
    public ComplaintResponse getComplaint(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    @GetMapping
    public List<ComplaintResponse> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @PutMapping("/{id}/status")
    public ComplaintResponse updateStatus(@PathVariable Long id,
                                          @RequestBody UpdateStatusRequest request) {
        request.setComplaintId(id);
        return complaintService.updateStatus(request);
    }

    @PutMapping("/{id}/escalate")
    public ComplaintResponse escalateComplaint(@PathVariable Long id) {

        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setComplaintId(id);
        request.setNewStatus(Status.ESCALATED);

        return complaintService.updateStatus(request);
    }

    @PutMapping("/{id}/resolve")
    public ComplaintResponse resolveComplaint(@PathVariable Long id) {

        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setComplaintId(id);
        request.setNewStatus(Status.RESOLVED);

        return complaintService.updateStatus(request);
    }

    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}