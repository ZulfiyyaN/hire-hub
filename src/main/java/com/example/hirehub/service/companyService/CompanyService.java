package com.example.hirehub.service.companyService;

import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.request.companyRequest.CompanyUpdateRequest;
import com.example.hirehub.model.response.companyResponse.CompanyRegisterResponse;
import com.example.hirehub.model.response.companyResponse.CompanyUpdateResponse;

public interface CompanyService {

    public CompanyRegisterResponse companyRegister (CompanyRegisterRequest request);
    public CompanyUpdateResponse companyUpdate(String email, CompanyUpdateRequest request);

}
