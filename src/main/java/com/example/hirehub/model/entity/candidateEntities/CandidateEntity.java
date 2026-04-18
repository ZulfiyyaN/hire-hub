package com.example.hirehub.model.entity.candidateEntities;

import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.enumeration.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.LastModifiedDate;

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
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Role role = Role.CANDIDATE;

    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "last_update_candidate")
    LocalDateTime lastUpdate;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    CandidateInfoEntity candidateInfo;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    CandidatePasswordEntity passwordEntity;

    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;
}
