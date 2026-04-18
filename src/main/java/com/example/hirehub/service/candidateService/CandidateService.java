package com.example.hirehub.service.candidateService;

import com.example.hirehub.model.request.candidateRequest.CandidateRegisterRequest;
import com.example.hirehub.model.request.candidateRequest.CandidateUpdateRequest;
import com.example.hirehub.model.response.candidateResponse.CandidateRegisterResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateUpdateResponse;
import com.example.hirehub.model.response.jobPostingResponse.JobPostResponse;

import java.util.List;

public interface CandidateService {


    public CandidateRegisterResponse candidateRegister(CandidateRegisterRequest request);

    public CandidateUpdateResponse candidateUpdate(String email, CandidateUpdateRequest request);

    public boolean deleteProfilForCandidate(String email);

    public List<JobPostResponse> getAllActiveJobPosts();

    public boolean applyJob(Long id);
}
