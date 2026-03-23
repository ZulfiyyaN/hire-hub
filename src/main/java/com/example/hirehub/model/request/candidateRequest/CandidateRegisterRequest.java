package com.example.hirehub.model.request;

import com.example.hirehub.model.enumeration.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CandidateRegisterRequest {
    @NotBlank(message = "Name can not be blank!")
    String name;

    @NotBlank(message = "Surname can not be blank!")
    String surname;

    @NotBlank(message = "Password can not be blank!")
    @Size(min = 8, max = 20, message = "Symbol should be more than 8, less than 20")
    String password;

    @Email(message = "Email format is not correct ")
    @NotBlank(message = "Email info is important")
    String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+994|0)?(50|51|55|70|77|99|10|60)\\d{7}$",
            message = "Please enter a valid Azerbaijan mobile number (e.g. +994501234567)")
    String phone;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Past(message = "Date of birth should be past")
    @NotNull
    LocalDate dateOfBirth;
    String education;
    String workExperience;
    String knowledge;

}
