package com.example.hirehub.model.entity;

import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.enumeration.StatusApplication;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Table(name = "applications")
@Entity

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    CandidateEntity candidate;

    @ManyToOne
    @JoinColumn(name = "job_post_id")
    JobPostingEntity jobPosting;

    LocalDateTime applicationDate;

    StatusApplication status;


}
