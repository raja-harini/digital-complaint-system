package com.example.digcompsys.dto.request;

import com.example.digcompsys.model.Category;
import com.example.digcompsys.model.Priority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateComplaintRequest {

    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private Long userId;
}