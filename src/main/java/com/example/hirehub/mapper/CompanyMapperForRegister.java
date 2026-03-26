package com.example.hirehub.mapper;

import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyInfoEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyPasswordEntity;
import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.response.companyResponse.CompanyRegisterResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class CompanyMapperForRegister {

    PasswordEncoder passwordEncoder;

    public CompanyEntity toCompany(CompanyRegisterRequest request) {

        if (request == null) {
            log.warn("Request can not be null!");
            throw new NullPointerException("Request is null!");
        }
        CompanyEntity entity = new CompanyEntity();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());


        CompanyPasswordEntity passwordEntity = new CompanyPasswordEntity();
        passwordEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        CompanyInfoEntity companyInfo = new CompanyInfoEntity();
        companyInfo.setLocation(request.getLocation());
        companyInfo.setDescription(request.getDescription());
        companyInfo.setWebsite(request.getWebsite());
        companyInfo.setFoundedAt(request.getFoundedAt());

        entity.setCompanyInfo(companyInfo);
        entity.setCompanyPasswordEntity(passwordEntity);
        companyInfo.setCompany(entity);
        passwordEntity.setCompany(entity);

        return entity;
    }


    public CompanyRegisterResponse toResponse(CompanyEntity entity) {
        CompanyRegisterResponse response = new CompanyRegisterResponse();
        if (entity == null) {
            log.warn("Entity is null");
            throw new NullPointerException("Entity is null!");
        }
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        response.setLocation(entity.getCompanyInfo().getLocation());
        response.setWebsite(entity.getCompanyInfo().getWebsite());
        response.setDescription(entity.getCompanyInfo().getDescription());
        response.setFoundedAt(entity.getCompanyInfo().getFoundedAt());

        return response;
    }


}
