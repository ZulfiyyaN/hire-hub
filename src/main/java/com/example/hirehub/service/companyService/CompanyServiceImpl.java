package com.example.hirehub.service.companyService;

import com.example.hirehub.exception.AlreadyExistsException;
import com.example.hirehub.mapper.CompanyMapperForRegister;
import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.response.CandidateRegisterResponse;
import com.example.hirehub.model.response.CompanyRegisterResponse;
import com.example.hirehub.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;
    CompanyMapperForRegister companyMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public CompanyRegisterResponse register(CompanyRegisterRequest request) {
        if (companyRepository.existsByEmail(request.getEmail())) {
            log.warn("Email {} already exists", request.getEmail());
            throw new AlreadyExistsException("Candidate with this email already exists");
        }
        CompanyEntity companyEntity = companyMapper.toCompany(request);
        companyEntity = companyRepository.save(companyEntity);
        CompanyRegisterResponse response = companyMapper.toResponse(companyEntity);
        log.info("{} is registered", request.getName());
        return response;
    }
}
