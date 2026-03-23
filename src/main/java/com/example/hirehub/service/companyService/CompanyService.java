package com.example.hirehub.service.companyService;

import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.response.CompanyRegisterResponse;

public interface CompanyService {

    public CompanyRegisterResponse register (CompanyRegisterRequest request);

}
