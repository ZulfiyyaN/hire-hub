package com.example.hirehub.model.request.candidateRequest;

import com.example.hirehub.model.enumeration.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CandidateUpdateRequest {

    String name;
    String surname;

    @Pattern(regexp = "^(\\+994|0)?(50|51|55|70|77|99|10|60)\\d{7}$",
            message = "Please enter a valid Azerbaijan mobile number (e.g. +994501234567)")
    String phone;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Past(message = "Date of birth should be past")
    LocalDate dateOfBirth;
    String education;
    String workExperience;
    String knowledge;

}
