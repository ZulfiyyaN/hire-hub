package com.example.hirehub.controller;

import com.example.hirehub.model.request.companyRequest.CompanyUpdateRequest;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingCreateRequest;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingUpdateRequest;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingCreateResponse;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingUpdateResponse;
import com.example.hirehub.service.jobPostingService.JobPostingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job_posting")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobPostingController {
    JobPostingService jobPostingService;

    @PostMapping("/create")
    public ResponseEntity<JobPostingCreateResponse> createJobPost(@RequestBody @Valid JobPostingCreateRequest request,
                                                                  Authentication authentication){
        String email = authentication.getName();
       JobPostingCreateResponse response =  jobPostingService.createJobPost(email, request);
        System.out.println(email);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<JobPostingUpdateResponse> updateJobPost(@RequestBody @Valid JobPostingUpdateRequest request,
                                                                  Authentication authentication ){
        String email = authentication.getName();
        JobPostingUpdateResponse response = jobPostingService.updateJobPost(email, request);
        return  ResponseEntity.ok(response);
    }


}
