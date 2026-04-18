package com.example.hirehub.controller;

import com.example.hirehub.exception.AlreadyExistsException;
import com.example.hirehub.model.request.candidateRequest.CandidateRegisterRequest;
import com.example.hirehub.model.request.candidateRequest.CandidateUpdateRequest;
import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.response.AuthResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateRegisterResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateUpdateResponse;
import com.example.hirehub.model.response.companyResponse.CompanyRegisterResponse;
import com.example.hirehub.model.response.jobPostingResponse.JobPostResponse;
import com.example.hirehub.repository.JobPostingRepository;
import com.example.hirehub.service.candidateService.CandidateService;
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

@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CandidateController {

    CandidateService candidateService;

    @PostMapping("/register")
    public ResponseEntity<CandidateRegisterResponse> register(@RequestBody @Valid CandidateRegisterRequest request) {
        CandidateRegisterResponse response = candidateService.candidateRegister(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInfo(@RequestBody @Valid CandidateUpdateRequest request,
                                        Authentication authentication) {
        if (authentication == null) {
            log.warn("Access denied: No authentication found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Candidate not found!");
        }
        String email = authentication.getName();
        CandidateUpdateResponse response = candidateService.candidateUpdate(email, request);
        System.out.println(email);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/delete")
    public ResponseEntity deleteCandidate(Authentication authentication) {
        if (authentication == null) {
            log.warn("Access denied: No authentication found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Candidate not found!");
        }
        String email = authentication.getName();
        candidateService.deleteProfilForCandidate(email);
        return ResponseEntity.ok("Candidate is deleted successfully!");
    }


    @GetMapping("/all_job_posts")
    public ResponseEntity<List<JobPostResponse>> getAllJobPosts(Authentication authentication) {
        List<JobPostResponse> jobPosts = candidateService.getAllActiveJobPosts();
        return ResponseEntity.ok(jobPosts);
    }


    @PostMapping("apply_job/{id}")
    public ResponseEntity<?> applyJob(@PathVariable Long id){
        Boolean result = candidateService.applyJob(id);
        return ResponseEntity.ok(result);
    }



    @GetMapping("/test")
    public String test() {

        throw  new AlreadyExistsException("Yoxlamaq");
    }

}
