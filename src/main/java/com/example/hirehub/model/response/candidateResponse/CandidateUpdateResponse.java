package com.example.hirehub.model.response.candidateResponse;

import com.example.hirehub.model.enumeration.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CandidateUpdateResponse {
    String name;
    String surname;
    String email;
    String phone;
    Gender gender;
    LocalDate dateOfBirth;
    String education;
    String workExperience;
    String knowledge;


}
