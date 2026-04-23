package com.example.hirehub.controller;

import com.example.hirehub.exception.AlreadyExistsException;
import com.example.hirehub.model.enumeration.StatusApplication;
import com.example.hirehub.model.enumeration.StatusJobPost;
import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.request.companyRequest.CompanyUpdateRequest;
import com.example.hirehub.model.response.ApplicationResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateResponse;
import com.example.hirehub.model.response.companyResponse.CompanyRegisterResponse;
import com.example.hirehub.model.response.companyResponse.CompanyUpdateResponse;
import com.example.hirehub.repository.ApplicationRepository;
import com.example.hirehub.service.candidateService.CandidateService;
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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {
    CompanyService companyService;
    CandidateService candidateService;
    private final ApplicationRepository applicationRepository;


    @PostMapping("/register")
    public ResponseEntity<CompanyRegisterResponse> register(@RequestBody @Valid CompanyRegisterRequest request) {
        CompanyRegisterResponse response = companyService.companyRegister(request);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateCompany(@RequestBody @Valid CompanyUpdateRequest request,
                                           Authentication authentication) {
        if (authentication == null) {
            log.warn("Access denied: No authentication found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }
        String email = authentication.getName();
        CompanyUpdateResponse response = companyService.companyUpdate(email, request);
        System.out.println(email);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete")
    public ResponseEntity deleteCompany(Authentication authentication) {
        if (authentication == null) {
            log.warn("Access denied: No authentication found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }
        String email = authentication.getName();
        companyService.deleteProfilForCompany(email);
        return ResponseEntity.ok("Company is deleted successfully!");
    }


    @GetMapping("/all_candidates")
    public ResponseEntity<List<CandidateResponse>> getAllCandidates() {
        List<CandidateResponse> all = companyService.getAllCandidates();
        return ResponseEntity.ok(all);
    }


    @GetMapping("/all_applications")
    public ResponseEntity<?> getAllApplied(Authentication auth) {
        List<ApplicationResponse> result = companyService.getAllApplications(auth.getName());
        return ResponseEntity.ok(result);

    }


    @PutMapping("/change_status_application/{id}")
    public ResponseEntity<?> changestatusApplication(@PathVariable Long id,
                                                     @RequestParam StatusApplication status,
                                                     Authentication auth) {
        Boolean result = companyService.changeStatusApplication(auth.getName(), id, status);
        return ResponseEntity.ok(result);
    }





    @GetMapping("/test")
    public String test() {

        throw new AlreadyExistsException("Yoxlamaq");
    }


}
