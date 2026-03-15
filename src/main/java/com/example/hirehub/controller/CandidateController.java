package com.example.hirehub.controller;

import com.example.hirehub.model.request.CandidateRegisterRequest;
import com.example.hirehub.model.response.CandidateRegisterResponse;
import com.example.hirehub.service.candidateService.CandidateService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandidateController {

    CandidateService candidateService;

    @PostMapping("/register")
    public ResponseEntity<CandidateRegisterResponse> register(@RequestBody @Valid CandidateRegisterRequest request) {
        CandidateRegisterResponse response = candidateService.candidateRegister(request);
        return ResponseEntity.ok(response);
    }


}
