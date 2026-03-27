package com.example.hirehub.service.jobPostingService;

import com.example.hirehub.model.request.jobPostingRequest.JobPostingCreateRequest;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingUpdateRequest;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingCreateResponse;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingUpdateResponse;

public interface JobPostingService {

    public JobPostingCreateResponse createJobPost(String email, JobPostingCreateRequest request);

    public JobPostingUpdateResponse updateJobPost(String email, JobPostingUpdateRequest request);




}
