package com.example.hirehub.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {

    LocalDateTime timestamp;
//    int status;          // 400, 404, 500
//    String errorCode;    // "VALIDATION_FAILED", "USER_NOT_FOUND"
    String message;      // "Məlumatlar düzgün deyil"
    String path;         // "/api/auth/login"
    Map<String, String> details; // Sahə adları və xəta mesajları (məs: "email": "Səhv format")

}
