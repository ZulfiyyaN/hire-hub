package com.example.hirehub.model.entity.companyEntities;

import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.enumeration.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLRestriction("status = 'ACTIVE'")
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
    @LastModifiedDate
    LocalDateTime lastUpdate;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private CompanyInfoEntity companyInfo;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    CompanyPasswordEntity companyPasswordEntity;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    List<JobPostingEntity> jobPosting;

    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

}
