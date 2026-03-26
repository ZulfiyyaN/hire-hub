package com.example.hirehub.controller;

import com.example.hirehub.model.request.jobPostingRequest.JobPostingCreateRequest;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingCreateResponse;
import com.example.hirehub.service.jobPostingService.JobPostingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job_posting")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobPostingController {
    JobPostingService jobPostingService;

    @PostMapping("/create")
    public ResponseEntity<JobPostingCreateResponse> createJobPost(String email, @PathVariable @Valid JobPostingCreateRequest request){
       JobPostingCreateResponse response =  jobPostingService.createJobPost(email, request);
        return ResponseEntity.ok(response);
    }

}
