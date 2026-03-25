package com.example.hirehub.model.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CompanyUpdateResponse {

    String name;
    String location;
    String website;
    String description;
    LocalDate foundedAt;


}
