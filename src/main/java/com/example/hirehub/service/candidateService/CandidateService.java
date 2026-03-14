package com.example.hirehub.service.candidateService;

import com.example.hirehub.model.request.CandidateRegisterRequest;
import com.example.hirehub.model.response.CandidateRegisterResponse;

public interface CandidateService {


    public CandidateRegisterResponse candidateRegister(CandidateRegisterRequest request);

}
