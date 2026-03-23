package com.example.hirehub.model.entity.companyEntities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "companies_info")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String location;
    @Column(nullable = false, unique = true)
    String website;
    @Column(columnDefinition = "TEXT")
    String description; // info about company
    @Column(name = "founded_at")
    LocalDate foundedAt;
    @OneToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;


}

