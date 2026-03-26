package com.example.hirehub.model.response.companyResponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CompanyRegisterResponse {

    String name;
    String email;
    String location;
    String website;
    String description;
    LocalDate foundedAt;
}
