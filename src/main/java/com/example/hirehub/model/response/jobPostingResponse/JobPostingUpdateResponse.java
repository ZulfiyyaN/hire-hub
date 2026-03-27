package com.example.hirehub.model.response.jobPostingResponse;

import com.example.hirehub.model.enumeration.WorkPlace;
import com.example.hirehub.model.enumeration.WorkType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobPostingUpdateResponse {
    String jobTitle;
    String eduReq;      //Education Requirements
    String expLevel;//Experience Level
    String skills;     //Skills & Qualifications
    String location;
    WorkType workType;
    WorkPlace workPlace;  //Remote, On-Site or Hybrid
    Double salary;
    String position;
    LocalDateTime expiredDate;
}
