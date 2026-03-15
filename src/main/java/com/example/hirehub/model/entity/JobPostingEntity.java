package com.example.hirehub.model.entity;


import com.example.hirehub.model.enumeration.Status;
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
public class JobPostingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    Status jobPostingStatus = Status.PENDING;
    String companyName;
    String jobTitle;
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAtProfile = LocalDateTime.now();
    LocalDateTime expiredDate;
    @OneToOne(mappedBy = "jobPostingEntity", cascade = CascadeType.ALL)
    private JobPostingInfoEntity jobPostingInfoEntity;



}
