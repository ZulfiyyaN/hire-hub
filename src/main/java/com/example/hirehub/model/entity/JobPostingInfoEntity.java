package com.example.hirehub.model.entity;

import com.example.hirehub.model.enumeration.WorkPlace;
import com.example.hirehub.model.enumeration.WorkType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_postings")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobPostingInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    String companyName;
    String jobTitle;
    String eduReq;      //Education Requirements
    String expLevel;      //Experience Level
    String skills;     //Skills & Qualifications
    String location;
    @Enumerated(EnumType.STRING)
    WorkType workType;   //Full-time, Part-time etc.
    @Enumerated(EnumType.STRING)
    WorkPlace workPlace;  //Remote, On-Site or Hybrid
    Double salary;
    String position;
    String email;
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAtProfile = LocalDateTime.now();
    LocalDateTime expiredDate;




    @OneToOne
    @JoinColumn(name = "jobPosting_id")
    private JobPostingEntity jobPostingEntity;

}
