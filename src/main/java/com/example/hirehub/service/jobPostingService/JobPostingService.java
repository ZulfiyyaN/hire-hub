package com.example.hirehub.service.jobPostingService;

import com.example.hirehub.model.request.jobPostingRequest.JobPostingCreateRequest;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingCreateResponse;

public interface JobPostingService {

    public JobPostingCreateResponse createJobPost(String email, JobPostingCreateRequest request);

}
