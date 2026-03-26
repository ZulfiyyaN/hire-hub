package com.example.hirehub.model.request.jobPostingRequest;

import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingInfoEntity;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.enumeration.WorkPlace;
import com.example.hirehub.model.enumeration.WorkType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class JobPostingCreateRequest {

    @NotBlank(message = "Job title can not be blank!")
    String jobTitle;
    String eduReq;      //Education Requirements
    String expLevel;//Experience Level
    @NotBlank(message = "Skills can not be blank!")
    String skills;     //Skills & Qualifications
    String location;
    WorkType workType;
    WorkPlace workPlace;  //Remote, On-Site or Hybrid
    Double salary;
    String position;
    @Future (message = "Expired date should be future date")
    LocalDateTime expiredDate;




}

