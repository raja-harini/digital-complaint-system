package com.example.digcompsys.dto.request;

import com.example.digcompsys.model.Category;
import com.example.digcompsys.model.Priority;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateComplaintRequest {

    private String title;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Category category;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Priority priority;
}