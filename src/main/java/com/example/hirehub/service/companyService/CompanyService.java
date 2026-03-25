package com.example.hirehub.service.companyService;

import com.example.hirehub.model.request.candidateRequest.CandidateUpdateRequest;
import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.request.companyRequest.CompanyUpdateRequest;
import com.example.hirehub.model.response.CandidateUpdateResponse;
import com.example.hirehub.model.response.CompanyRegisterResponse;
import com.example.hirehub.model.response.CompanyUpdateResponse;

public interface CompanyService {

    public CompanyRegisterResponse companyRegister (CompanyRegisterRequest request);
    public CompanyUpdateResponse companyUpdate(String email, CompanyUpdateRequest request);

}
