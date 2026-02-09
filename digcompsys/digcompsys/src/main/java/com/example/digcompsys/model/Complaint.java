package com.example.digcompsys.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String category;

    private String priority;

    private String status;

    @OneToMany(mappedBy = "complaint")
    @JsonIgnore
    private List<ComplaintAssignment> assignments;

    @OneToMany(mappedBy = "complaint")
    private List<StatusHistory> histories;

    @OneToMany(mappedBy = "complaint")
    private List<Notification> notifications;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = true)
    @JoinColumn(name = "team_id")
    private Team team;

}
