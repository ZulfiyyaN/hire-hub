package com.example.hirehub.controller;

import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.request.companyRequest.CompanyUpdateRequest;
import com.example.hirehub.model.response.companyResponse.CompanyRegisterResponse;
import com.example.hirehub.model.response.companyResponse.CompanyUpdateResponse;
import com.example.hirehub.service.companyService.CompanyService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
    CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<CompanyRegisterResponse> register(@RequestBody @Valid CompanyRegisterRequest request) {
        CompanyRegisterResponse response = companyService.companyRegister(request);
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

    @DeleteMapping("/delete")
    public ResponseEntity deleteCompany(Authentication authentication){
        if(authentication==null){
            log.warn("Access denied: No authentication found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Company not found!");
        }
        String email = authentication.getName();
        companyService.deleteProfilForCompany(email);
        return ResponseEntity.ok("Company is deleted successfully!");
    }
}
