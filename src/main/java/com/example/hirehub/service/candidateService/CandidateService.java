package com.example.hirehub.service.candidateService;

import com.example.hirehub.model.request.CandidateRegisterRequest;
import com.example.hirehub.model.request.CandidateUpdateRequest;
import com.example.hirehub.model.response.CandidateRegisterResponse;
import com.example.hirehub.model.response.CandidateUpdateResponse;

public interface CandidateService {


    public CandidateRegisterResponse candidateRegister(CandidateRegisterRequest request);

    public CandidateUpdateResponse candidateUpdate(String email, CandidateUpdateRequest request);

}
