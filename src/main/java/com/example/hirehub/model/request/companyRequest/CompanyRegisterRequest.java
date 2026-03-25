package com.example.hirehub.model.request.companyRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CompanyRegisterRequest {

    @NotBlank(message = "Name can not be blank!")
    String name;
    @Email(message = "Email format is not correct ")
    @NotBlank(message = "Email info is important")
    String email;
    @NotBlank(message = "Password can not be blank!")
    @Size(min = 8, max = 20, message = "Symbol should be more than 8, less than 20")
    String password;
    String location;
    String website;
    String description;
    @Past(message = "Date of foundation should be past")
    LocalDate foundedAt;
}
