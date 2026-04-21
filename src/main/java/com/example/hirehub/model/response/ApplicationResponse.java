package com.example.hirehub.model.response;

import com.example.hirehub.model.enumeration.StatusApplication;
import com.example.hirehub.model.response.candidateResponse.CandidateResponse;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApplicationResponse {

    Long id;
    CandidateResponse candidate;
    String jobTitle;
    LocalDateTime expiredDate;
    StatusApplication status;
    LocalDateTime applicationDate;



}
