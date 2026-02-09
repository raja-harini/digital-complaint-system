package com.example.digcompsys.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import lombok.*;


@Entity
@Table(name = "complaint_assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    private String status;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

    @ManyToOne(optional = true)
    @JoinColumn(name = "team_id")
    private Team team;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}