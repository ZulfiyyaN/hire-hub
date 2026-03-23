package com.example.hirehub.model.request.companyRequest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyLoginRequest {
    String email;
    String password;
}
