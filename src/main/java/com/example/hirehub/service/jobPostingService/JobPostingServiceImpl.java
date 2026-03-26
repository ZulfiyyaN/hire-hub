package com.example.hirehub.service.jobPostingService;

import com.example.hirehub.exception.CompanyNotFoundException;
import com.example.hirehub.mapper.JobPostingMapperForCreate;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingCreateRequest;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingCreateResponse;
import com.example.hirehub.repository.CompanyRepository;
import com.example.hirehub.repository.JobPostingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {
    CompanyRepository companyRepository;
    JobPostingMapperForCreate jobPostingMapperForCreate;
    JobPostingRepository jobPostingRepository;

    @Override
    public JobPostingCreateResponse createJobPost(String email, JobPostingCreateRequest request) {
        if (!companyRepository.existsByEmail(email)) {
            log.warn("Email is wrong!");
            throw new CompanyNotFoundException("Company not found!");
        }
        JobPostingEntity entity = jobPostingMapperForCreate.toEntity(request);
        JobPostingCreateResponse response = jobPostingMapperForCreate.toResponse(entity);

        jobPostingRepository.save(entity);
        return response;
    }
}
