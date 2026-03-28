package com.example.hirehub.service.jobPostingService;

import com.example.hirehub.exception.CompanyNotFoundException;
import com.example.hirehub.mapper.JobPostingMapperForCreate;
import com.example.hirehub.mapper.JobPostingMapperForUpdate;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingInfoEntity;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingCreateRequest;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingUpdateRequest;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingCreateResponse;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingUpdateResponse;
import com.example.hirehub.repository.CompanyRepository;
import com.example.hirehub.repository.JobPostingRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobPostingServiceImpl implements JobPostingService {
    CompanyRepository companyRepository;
    JobPostingMapperForCreate jobPostingMapperForCreate;
    JobPostingRepository jobPostingRepository;
    JobPostingMapperForUpdate jobPostingMapperForUpdate;

    @Override
    public JobPostingCreateResponse createJobPost(String email, JobPostingCreateRequest request) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByEmail(email);
        if (companyEntity.isEmpty()) {
            log.warn("Email is wrong!");
            throw new CompanyNotFoundException("Company not found!");
        }
        JobPostingEntity entity = jobPostingMapperForCreate.toEntity(request);
        entity.setCompany(companyEntity.get());
        jobPostingRepository.save(entity);
        JobPostingCreateResponse response = jobPostingMapperForCreate.toResponse(entity);

        return response;
    }

    @Override
    public JobPostingUpdateResponse updateJobPost(String email, JobPostingUpdateRequest request) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByEmail(email);
        if (companyEntity.isEmpty()) {
            log.warn("Email is wrong!");
            throw new CompanyNotFoundException("Company not found!");
        }
        JobPostingEntity entity = new JobPostingEntity();
        if (request.getJobTitle() != null) {
            entity.setJobTitle(request.getJobTitle());
        }
        if (request.getExpiredDate() != null) {
            entity.setExpiredDate(request.getExpiredDate());
        }
        JobPostingInfoEntity infoEntity = new JobPostingInfoEntity();
        if (request.getEduReq() != null) {
            infoEntity.setEduReq(request.getEduReq());
        }
        if (request.getExpLevel() != null) {
            infoEntity.setExpLevel(request.getExpLevel());
        }
        if (request.getSkills() != null) {
            infoEntity.setSkills(request.getSkills());
        }
        if (request.getLocation() != null) {
            infoEntity.setLocation(request.getLocation());
        }
        if (request.getWorkType() != null) {
            infoEntity.setWorkType(request.getWorkType());
        }
        if (request.getWorkPlace() != null) {
            infoEntity.setWorkPlace(request.getWorkPlace());
        }
        if (request.getSalary() != null) {
            infoEntity.setSalary(request.getSalary());
        }
        if (request.getPosition() != null) {
            infoEntity.setPosition(request.getPosition());
        }

        entity.setJobPostingInfoEntity(infoEntity);
        entity.setCompany(companyEntity.get());
        infoEntity.setJobPostingEntity(entity);
        jobPostingRepository.save(entity);
        JobPostingUpdateResponse response = jobPostingMapperForUpdate.toResponseForUpdate(entity);
        return response;
    }
}
