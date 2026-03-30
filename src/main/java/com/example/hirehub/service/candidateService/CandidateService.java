package com.example.hirehub.service.candidateService;

import com.example.hirehub.model.request.candidateRequest.CandidateRegisterRequest;
import com.example.hirehub.model.request.candidateRequest.CandidateUpdateRequest;
import com.example.hirehub.model.response.candidateResponse.CandidateRegisterResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateUpdateResponse;

public interface CandidateService {


    public CandidateRegisterResponse candidateRegister(CandidateRegisterRequest request);

    public CandidateUpdateResponse candidateUpdate(String email, CandidateUpdateRequest request);

    public boolean deleteProfilForCandidate(String email);
}
