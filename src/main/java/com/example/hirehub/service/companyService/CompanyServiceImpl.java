package com.example.hirehub.service.companyService;

import com.example.hirehub.exception.AlreadyExistsException;
import com.example.hirehub.exception.CompanyNotFoundException;
import com.example.hirehub.mapper.CompanyMapperForRegister;
import com.example.hirehub.mapper.CompanyMapperForUpdate;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.request.companyRequest.CompanyUpdateRequest;
import com.example.hirehub.model.response.CompanyRegisterResponse;
import com.example.hirehub.model.response.CompanyUpdateResponse;
import com.example.hirehub.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;
    CompanyMapperForRegister companyMapperForRegister;
    CompanyMapperForUpdate companyMapperForUpdate;

    PasswordEncoder passwordEncoder;

    @Override
    public CompanyRegisterResponse companyRegister(CompanyRegisterRequest request) {
        if (companyRepository.existsByEmail(request.getEmail())) {
            log.warn("Email {} already exists", request.getEmail());
            throw new AlreadyExistsException("Candidate with this email already exists");
        }
        CompanyEntity companyEntity = companyMapperForRegister.toCompany(request);
        companyEntity = companyRepository.save(companyEntity);
        CompanyRegisterResponse response = companyMapperForRegister.toResponse(companyEntity);
        log.info("{} is registered", request.getName());
        return response;
    }

    @Override
    public CompanyUpdateResponse companyUpdate(String email, CompanyUpdateRequest request) {
        Optional<CompanyEntity> entity = companyRepository.findByEmail(email);
        if (entity.isEmpty()) {
            log.warn("Company not found with {} ", email);
            throw new CompanyNotFoundException("Company not found: " + email);
        }
        if (request.getName() != null) {
            entity.get().setName(request.getName());
        }
        if (request.getLocation() != null) {
            entity.get().getCompanyInfo().setLocation(request.getLocation());
        }
        if (request.getWebsite() != null) {
            entity.get().getCompanyInfo().setWebsite(request.getWebsite());
        }
        if (request.getDescription() != null) {
            entity.get().getCompanyInfo().setDescription(request.getDescription());
        }
        if (request.getFoundedAt() != null) {
            entity.get().getCompanyInfo().setFoundedAt(request.getFoundedAt());
        }
        entity.get().setLastUpdate(LocalDateTime.now());

        CompanyUpdateResponse response = companyMapperForUpdate.toResponseForUpdate(entity.get());
        return response;
    }
}
