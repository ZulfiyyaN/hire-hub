package com.example.hirehub.mapper;

import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyInfoEntity;
import com.example.hirehub.model.response.companyResponse.CompanyUpdateResponse;
import com.example.hirehub.repository.CompanyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class CompanyMapperForUpdate {
    CompanyRepository companyRepository;

    public CompanyUpdateResponse toResponseForUpdate(CompanyEntity entity) {
        CompanyInfoEntity info = entity.getCompanyInfo();
        CompanyUpdateResponse response = new CompanyUpdateResponse();

        response.setName(entity.getName());
        response.setLocation(entity.getCompanyInfo().getLocation());
        response.setWebsite(entity.getCompanyInfo().getWebsite());
        response.setDescription(entity.getCompanyInfo().getDescription());
        response.setFoundedAt(entity.getCompanyInfo().getFoundedAt());

        return response;
    }


}
