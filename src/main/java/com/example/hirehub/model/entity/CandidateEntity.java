package com.example.hirehub.model.entity;

import com.example.hirehub.model.enumeration.CandidateStatus;
import com.example.hirehub.model.enumeration.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

    @Entity
    @Table(name = "candidates")
    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class CandidateEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String name;
        String surname;
        @Column(unique = true, nullable = false)
        String email;
        @Column(unique = true, nullable = false)
        String phone;
        @Column(nullable = false)
        Gender gender;

        // String password;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        CandidateStatus status = CandidateStatus.PENDING;
        @Column(name = "created_at", updatable = false)
        LocalDateTime createdAt = LocalDateTime.now();
        @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
        CandidateInfoEntity candidateInfo;
}
