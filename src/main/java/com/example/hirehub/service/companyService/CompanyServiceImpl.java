package com.example.hirehub.service.companyService;

import com.example.hirehub.exception.AlreadyExistsException;
import com.example.hirehub.exception.CompanyNotFoundException;
import com.example.hirehub.mapper.ApplicationMapper;
import com.example.hirehub.mapper.CompanyMapperForRegister;
import com.example.hirehub.mapper.CompanyMapperForUpdate;
import com.example.hirehub.model.entity.ApplicationEntity;
import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.request.companyRequest.CompanyRegisterRequest;
import com.example.hirehub.model.request.companyRequest.CompanyUpdateRequest;
import com.example.hirehub.model.response.ApplicationResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateResponse;
import com.example.hirehub.model.response.companyResponse.CompanyRegisterResponse;
import com.example.hirehub.model.response.companyResponse.CompanyUpdateResponse;
import com.example.hirehub.repository.ApplicationRepository;
import com.example.hirehub.repository.CandidateRepository;
import com.example.hirehub.repository.CompanyRepository;
import com.example.hirehub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    CandidateRepository candidateRepository;
    ApplicationRepository applicationRepository;
    ApplicationMapper applicationMapper;

    @Override
    public CompanyRegisterResponse companyRegister(CompanyRegisterRequest request) {
        if (companyRepository.existsByEmail(request.getEmail())) {
            log.warn("Email {} already exists", request.getEmail());
            throw new AlreadyExistsException("Candidate with this email already exists");
        }
        CompanyEntity companyEntity = companyMapperForRegister.toCompany(request);

        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.COMPANY);
        user.setStatus(Status.ACTIVE);

        companyEntity.setUser(user);
        user.setCompanyEntity(companyEntity);

        companyRepository.save(companyEntity);
        userRepository.save(user);

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

    @Override
    public boolean deleteProfilForCompany(String email) {
        Optional<UserEntity> optionalCompany = userRepository.findByEmail(email);
        if (optionalCompany.isEmpty()) {
            log.warn("User not found: {}", email);
            throw new CompanyNotFoundException("Company not found: " + email);
        }
        optionalCompany.get().setStatus(Status.DELETED);
        userRepository.save(optionalCompany.get());
        return true;
    }


    @Override
    public List<CandidateResponse> getAllCandidates() {
        List<CandidateEntity> allCandidates = candidateRepository.findAll();
        List<CandidateResponse> allResponses = allCandidates.stream()
                .filter(a -> a.getUser() != null && a.getUser().getStatus() == Status.ACTIVE)
                .map(c -> new CandidateResponse(
                                c.getId(),
                                c.getName(),
                                c.getSurname(),
                                c.getEmail(),
                                c.getPhone(),
                                c.getGender().name(),
                                c.getCandidateInfo().getDateOfBirth(),
                                c.getCandidateInfo().getEducation(),
                                c.getCandidateInfo().getWorkExperience(),
                                c.getCandidateInfo().getKnowledge()
                        )
                )
                .toList();
        return allResponses;
    }


    @Override
    public List<ApplicationResponse> getAllApplications(String email) {
        List<ApplicationEntity> allApplications = applicationRepository.findAll();
        List<ApplicationResponse> responses = allApplications.stream()
                .filter(a -> a.getJobPosting().getCompany().getEmail().equals(email))
                .map(m -> applicationMapper.toResponse(m))
                .toList();
        return responses;
    }
}
