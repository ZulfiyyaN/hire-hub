package com.example.hirehub.model.response;


import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.enumeration.StatusApplication;
import com.example.hirehub.model.enumeration.StatusJobPost;
import com.example.hirehub.model.enumeration.WorkPlace;
import com.example.hirehub.model.enumeration.WorkType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationForCandidateResponse {
    Long id;
    String jobTitle;
    String eduReq;      //Education Requirements
    String expLevel;      //Experience Level
    String skills;     //Skills & Qualifications
    String location;
    WorkType workType;   //Full-time, Part-time etc.
    WorkPlace workPlace;  //Remote, On-Site or Hybrid
    Double salary;
    String position;
    String companyName;
    String CompanyEmail;
    LocalDateTime createdAtProfile;
    LocalDateTime expiredDate;
    StatusJobPost status;
    LocalDateTime applicationDate;
    StatusApplication statusApplication;
    LocalDateTime decisionDate;



}
