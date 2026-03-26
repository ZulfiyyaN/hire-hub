package com.example.hirehub.controller;

import com.example.hirehub.model.request.companyRequest.CompanyLoginRequest;
import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.request.companyRequest.CompanyUpdateRequest;
import com.example.hirehub.model.response.*;
import com.example.hirehub.model.response.companyResponse.CompanyRegisterResponse;
import com.example.hirehub.model.response.companyResponse.CompanyUpdateResponse;
import com.example.hirehub.service.companyService.AuthServiceForCompany;
import com.example.hirehub.service.companyService.CompanyService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
    CompanyService companyService;
    AuthServiceForCompany authService;

    @PostMapping("/register")
    public ResponseEntity<CompanyRegisterResponse> register(@RequestBody @Valid CompanyRegisterRequest request) {
        CompanyRegisterResponse response = companyService.companyRegister(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody CompanyLoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<CompanyUpdateResponse> updateInfo(@RequestBody @Valid CompanyUpdateRequest request,
                                                            Authentication authentication) {
        String email = authentication.getName();
        CompanyUpdateResponse response = companyService.companyUpdate(email, request);
        System.out.println(email);
        return ResponseEntity.ok(response);
    }
}
