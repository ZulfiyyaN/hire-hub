package com.example.hirehub.model.entity.jobPostingEntities;


import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.enumeration.StatusJobPost;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_postings")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLRestriction("status = 'ACTIVE'")
public class JobPostingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String jobTitle;
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAtProfile = LocalDateTime.now();
    LocalDateTime expiredDate;
    @LastModifiedDate
    LocalDateTime lastUpdate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    StatusJobPost status=StatusJobPost.ACTIVE;
    @OneToOne(mappedBy = "jobPostingEntity", cascade = CascadeType.ALL)
    JobPostingInfoEntity jobPostingInfoEntity;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    CompanyEntity company;


}
