package com.example.digcompsys.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class ComplaintRequest {
    @NotBlank(message = "Title is Required")
    private String title;
    @NotBlank(message = "Description should not be Empty")
    private String description;
    @NotBlank(message = "Category should be chosen")
    private String category;
    @NotBlank(message = "Priority should be selected")
    private String priority;
}
