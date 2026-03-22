package com.example.hirehub.model.response;

import com.example.hirehub.model.enumeration.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
