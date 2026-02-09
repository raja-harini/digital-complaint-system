package com.example.digcompsys.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public enum Role {
        ROLE_ADMIN,
        ROLE_EMPLOYEE,
        ROLE_USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role roleName;

    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Complaint> complaints;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<StatusHistory> histories;

    @OneToMany(mappedBy = "user")
    private List<ComplaintAssignment> assignments;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
