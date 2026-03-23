package com.example.digcompsys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 link to employee
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ✅ use existing enum
    @Enumerated(EnumType.STRING)
    private Category fieldOfWork;

    // role interested
    private String preferredRole;

    // file storage (simple way)
    @Lob
    private byte[] document;

    private String fileName;
}