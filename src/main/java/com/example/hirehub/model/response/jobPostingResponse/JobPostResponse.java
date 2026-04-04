package com.example.hirehub.model.response.jobPostingResponse;

import com.example.hirehub.model.response.companyResponse.CompanyShortResponse;

import java.time.LocalDateTime;

public record JobPostResponse(Long id,
                              String jobTitle,
                              String location,
                              String position,
                              Double salary,
                              String workType, // Enum-u String kimi gonderirik.
                              String workPlace,
                              String expLevel,
                              String eduReq,
                              String skills,
                              LocalDateTime expiredDate,
                              CompanyShortResponse company ) {
}
