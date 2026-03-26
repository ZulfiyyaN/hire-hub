package com.example.hirehub.model.response.candidateResponse;

import com.example.hirehub.model.enumeration.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateRegisterResponse {
    String name;
    String surname;
    String email;
    String phone;
    Gender gender;



}
