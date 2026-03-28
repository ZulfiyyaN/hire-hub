package com.example.hirehub.model.request.jobPostingRequest;

import com.example.hirehub.model.enumeration.WorkPlace;
import com.example.hirehub.model.enumeration.WorkType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class JobPostingCreateRequest {

    @NotBlank(message = "Job title can not be blank!")
    String jobTitle;
    String eduReq;      //Education Requirements
    String expLevel;//Experience Level
    @NotBlank(message = "Skills can not be blank!")
    String skills;     //Skills & Qualifications
    String location;
    WorkType workType; // FULLTIME, PARTTIME, CONTRACT, FREELANCE, VOLUNTEER;
    WorkPlace workPlace;  // REMOTE, ON_SITE, HYBRID
    Double salary;
    String position;
    @Future (message = "Expired date should be future date")
    LocalDateTime expiredDate;




}

