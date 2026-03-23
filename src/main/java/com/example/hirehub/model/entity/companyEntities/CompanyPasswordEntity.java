package com.example.hirehub.model.entity.companyEntities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="company_passwords")
public class CompanyPasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String password;
    @OneToOne
    @JoinColumn(name = "profile_id")
    CompanyEntity company;




}
