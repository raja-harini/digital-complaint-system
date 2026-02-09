package com.example.digcompsys.model;

import com.example.digcompsys.model.ComplaintAssignment;
import com.example.digcompsys.model.StatusHistory;
import com.example.digcompsys.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String teamName;
    private String contact;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<ComplaintAssignment> assignments;

    @OneToMany(mappedBy = "team")
    private List<StatusHistory> histories;
}
