package com.example.hirehub.model.request.candidateRequest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateLoginRequest {
    String email;
    String password;
}
