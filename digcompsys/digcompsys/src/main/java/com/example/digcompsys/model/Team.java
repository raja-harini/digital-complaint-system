package com.example.digcompsys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(nullable = false, unique = true)
    private String teamName;

    @Column(nullable = false)
    private String contact;

    @ManyToOne
    @JoinColumn(name = "team_lead_id")
    private User teamLead;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "team_employees",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> employees;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ComplaintAssignment> assignments;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusHistory> statusHistories;
}