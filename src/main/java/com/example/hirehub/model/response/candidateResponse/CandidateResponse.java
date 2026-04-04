package com.example.hirehub.model.response.candidateResponse;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CandidateResponse(Long id,
                                String name,
                                String surname,
                                String email,
                                String phone,
                                String gender,
                                @JsonFormat(pattern = "yyyy-MM-dd")
                                LocalDate dateOfBirth,
                                String education,
                                String workExperience,
                                String knowledge) {
}
