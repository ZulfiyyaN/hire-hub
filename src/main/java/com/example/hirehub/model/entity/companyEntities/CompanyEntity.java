package com.example.hirehub.model.entity.companyEntities;

import com.example.hirehub.model.entity.candidateEntities.CandidatePasswordEntity;
import com.example.hirehub.model.enumeration.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(nullable = false, unique = true)
    String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status = Status.PENDING; //by default
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt = LocalDateTime.now();
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private CompanyInfoEntity companyInfo;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    CompanyPasswordEntity companyPasswordEntity;


}
