package com.example.hirehub.model.entity.jobPostingEntities;


import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.enumeration.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_postings")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobPostingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    Status jobPostingStatus = Status.PENDING;
    String jobTitle;
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAtProfile = LocalDateTime.now();
    LocalDateTime expiredDate;
    @LastModifiedDate
    LocalDateTime lastUpdate;
    @OneToOne(mappedBy = "jobPostingEntity", cascade = CascadeType.ALL)
    JobPostingInfoEntity jobPostingInfoEntity;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    CompanyEntity company;


}
