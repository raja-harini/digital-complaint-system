package com.example.digcompsys.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "status_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    private String oldStatus;
    private String newStatus;
    private LocalDateTime escalationTime;
    private LocalDateTime resolutionTime;
    private Boolean activeFlag;

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