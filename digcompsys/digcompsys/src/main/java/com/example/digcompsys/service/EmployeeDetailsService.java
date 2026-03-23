package com.example.digcompsys.service;

import com.example.digcompsys.model.Category;
import com.example.digcompsys.model.EmployeeDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeDetailsService {

    void saveDetails(Category field, String role, MultipartFile file);

    EmployeeDetails getMyDetails();

    List<EmployeeDetails> getAll();

    EmployeeDetails getByUserId(Long userId);
}