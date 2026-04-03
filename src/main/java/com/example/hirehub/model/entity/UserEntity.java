package com.example.hirehub.model.entity;

import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.enumeration.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
@SQLRestriction("status = 'ACTIVE'")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Status status;

    @Enumerated(EnumType.STRING)
    Role role;

    @LastModifiedDate
    @Column(name = "last_update_status")
    LocalDateTime lastUpdate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    CandidateEntity candidateEntity;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    CompanyEntity companyEntity;

}
