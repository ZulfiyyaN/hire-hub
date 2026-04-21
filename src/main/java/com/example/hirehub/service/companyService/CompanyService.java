package com.example.hirehub.service.companyService;

import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.request.companyRequest.CompanyUpdateRequest;
import com.example.hirehub.model.response.ApplicationResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateResponse;
import com.example.hirehub.model.response.companyResponse.CompanyRegisterResponse;
import com.example.hirehub.model.response.companyResponse.CompanyUpdateResponse;
import com.example.hirehub.model.response.jobPostingResponse.JobPostResponse;

import java.util.List;

public interface CompanyService {

    public CompanyRegisterResponse companyRegister(CompanyRegisterRequest request);

    public CompanyUpdateResponse companyUpdate(String email, CompanyUpdateRequest request);

    public boolean deleteProfilForCompany(String email);

    public List<CandidateResponse> getAllCandidates();
    public List<ApplicationResponse> getAllApplications(String email);
}
