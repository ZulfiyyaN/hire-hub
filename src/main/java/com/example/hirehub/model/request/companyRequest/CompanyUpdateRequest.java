package com.example.hirehub.model.request.companyRequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CompanyUpdateRequest {

    @NotBlank(message = "Name can not be blank!")
    String name;
    String location;
    String website;
    String description;
    @Past(message = "Date of foundation should be past")
    LocalDate foundedAt;
}
