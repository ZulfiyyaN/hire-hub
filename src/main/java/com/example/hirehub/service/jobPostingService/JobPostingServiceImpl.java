package com.example.hirehub.service.jobPostingService;

import com.example.hirehub.exception.CompanyNotFoundException;
import com.example.hirehub.exception.JobPostingNotFoundException;
import com.example.hirehub.mapper.JobPostingMapperForCreate;
import com.example.hirehub.mapper.JobPostingMapperForUpdate;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingInfoEntity;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.enumeration.StatusJobPost;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingCreateRequest;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingUpdateRequest;
import com.example.hirehub.model.response.companyResponse.CompanyShortResponse;
import com.example.hirehub.model.response.jobPostingResponse.JobPostResponse;
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

import java.util.List;
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
        if(!companyEntity.get().getUser().getStatus().equals(Status.ACTIVE)){
            log.warn("Company status is not ACTIVE!");
            throw new CompanyNotFoundException("Company status is not ACTIVE!");
        }
        JobPostingEntity jobEntity = jobPostingMapperForCreate.toEntity(request);

        jobEntity.setCompany(companyEntity.get());

        jobPostingRepository.save(jobEntity);
        JobPostingCreateResponse response = jobPostingMapperForCreate.toResponse(jobEntity);
        log.info("Job posted successfully!");
        return response;
    }

    @Override
    public JobPostingUpdateResponse updateJobPost(String email, Long jobPostId, JobPostingUpdateRequest request) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByEmail(email);
        if (companyEntity.isEmpty()) {
            log.warn("Email is wrong!");
            throw new CompanyNotFoundException("Company not found!");
        }
        Optional<JobPostingEntity> optionalJobPosting = jobPostingRepository.findByIdNative(jobPostId);
        if (optionalJobPosting.isEmpty()) {
            log.warn("Job Post Id is wrong!");
            throw new JobPostingNotFoundException("Job Post not found!");
        }

        if(companyEntity.get().getId()!= optionalJobPosting.get().getCompany().getId()){
            log.warn("The company has not job post with {}", jobPostId);
            throw  new JobPostingNotFoundException("Job Post not found for the company");
        }

        if (request.getJobTitle() != null) {
            optionalJobPosting.get().setJobTitle(request.getJobTitle());
        }
        if (request.getExpiredDate() != null) {
            optionalJobPosting.get().setExpiredDate(request.getExpiredDate());
        }

        JobPostingInfoEntity infoEntity = optionalJobPosting.get().getJobPostingInfoEntity();

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

        optionalJobPosting.get().setJobPostingInfoEntity(infoEntity);
        infoEntity.setJobPostingEntity(optionalJobPosting.get());

        jobPostingRepository.save(optionalJobPosting.get());
        JobPostingUpdateResponse response = jobPostingMapperForUpdate.toResponseForUpdate(optionalJobPosting.get());
        return response;
    }

    @Override
    public boolean deleteJobPost(String email, Long jobPostId) {
        Optional<CompanyEntity> companyEntity = companyRepository.findByEmail(email);
        if (companyEntity.isEmpty()) {
            log.warn("Email is wrong!");
            throw new CompanyNotFoundException("Company not found!");
        }
        Optional<JobPostingEntity> optionalJobPosting = jobPostingRepository.findByIdNative(jobPostId);
        if (optionalJobPosting.isEmpty()) {
            log.warn("Job Post Id is wrong!");
            throw new JobPostingNotFoundException("Job Post not found!");
        }
        if(companyEntity.get().getId()!= optionalJobPosting.get().getCompany().getId()){
            log.warn("The company has not job post with {}", jobPostId);
            throw  new JobPostingNotFoundException("Job Post not found for the company");
        }

        optionalJobPosting.get().setStatus(StatusJobPost.DELETED);
        jobPostingRepository.save(optionalJobPosting.get());

        log.info("Post is deleted!");
        return true;
    }

        @Override
        public List<JobPostResponse> getAllActiveJobPosts() {
            List<JobPostingEntity> postings = jobPostingRepository.findAll();
            if (postings.isEmpty()) {
                log.warn("Active job post not found! ");
                throw new JobPostingNotFoundException(" Active job post not found!");
            }
            List<JobPostResponse> allActiveResponses = postings.stream()
                    .filter(a -> a.getStatus().equals(StatusJobPost.ACTIVE))
                    .map(post -> new JobPostResponse(
                            post.getJobTitle(),
                            post.getJobPostingInfoEntity().getLocation(),
                            post.getJobPostingInfoEntity().getPosition(),
                            post.getJobPostingInfoEntity().getSalary(),
                            post.getJobPostingInfoEntity().getWorkType().name(),
                            post.getJobPostingInfoEntity().getWorkPlace().name(),
                            post.getJobPostingInfoEntity().getExpLevel(),
                            post.getJobPostingInfoEntity().getEduReq(),
                            post.getJobPostingInfoEntity().getSkills(),
                            post.getJobPostingInfoEntity().getJobPostingEntity().getExpiredDate(),
                            new CompanyShortResponse(
                                    post.getCompany().getName(),
                                    post.getCompany().getEmail(),
                                    post.getCompany().getCompanyInfo().getWebsite())
                    ))
                    .toList();
            log.info("All active job posts: ");
            return allActiveResponses;
        }

    }

