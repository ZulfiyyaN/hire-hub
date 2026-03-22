package com.example.hirehub.controller;

import com.example.hirehub.model.request.CandidateLoginRequest;
import com.example.hirehub.model.request.CandidateRegisterRequest;
import com.example.hirehub.model.request.CandidateUpdateRequest;
import com.example.hirehub.model.response.AuthResponse;
import com.example.hirehub.model.response.CandidateRegisterResponse;
import com.example.hirehub.model.response.CandidateUpdateResponse;
import com.example.hirehub.service.candidateService.AuthService;
import com.example.hirehub.service.candidateService.CandidateService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandidateController {

    CandidateService candidateService;
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<CandidateRegisterResponse> register(@RequestBody @Valid CandidateRegisterRequest request) {
        CandidateRegisterResponse response = candidateService.candidateRegister(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody CandidateLoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<CandidateUpdateResponse> updateInfo(@RequestBody @Valid CandidateUpdateRequest request,
                                                              Authentication authentication) {
        String email = authentication.getName();
        CandidateUpdateResponse response = candidateService.candidateUpdate(email, request);
        System.out.println(email);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/test")
    public String test() {
        return "secured endpoint works";
    }

}
