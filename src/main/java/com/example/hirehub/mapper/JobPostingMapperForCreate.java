package com.example.hirehub.mapper;

import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingInfoEntity;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingCreateRequest;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingCreateResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class JobPostingMapperForCreate {


    public JobPostingEntity toEntity (JobPostingCreateRequest request){
        JobPostingEntity entity = new JobPostingEntity();
        entity.setJobTitle(request.getJobTitle());
        entity.setExpiredDate(request.getExpiredDate());

        JobPostingInfoEntity infoEntity = new JobPostingInfoEntity();
        infoEntity.setEduReq(request.getEduReq());
        infoEntity.setExpLevel(request.getExpLevel());
        infoEntity.setSkills(request.getSkills());
        infoEntity.setLocation(request.getLocation());
        infoEntity.setWorkType(request.getWorkType());
        infoEntity.setSalary(request.getSalary());
        infoEntity.setPosition(request.getPosition());

        entity.setJobPostingInfoEntity(infoEntity);
        infoEntity.setJobPostingEntity(entity);

        return entity;
    }


    public JobPostingCreateResponse toResponse(JobPostingEntity entity){
        JobPostingCreateResponse response = new JobPostingCreateResponse();
        response.setCompanyName(entity.getCompany().getName());
        response.setCompanyEmail(entity.getCompany().getEmail());
        response.setJobTitle(entity.getJobTitle());
        response.setExpiredDate(entity.getExpiredDate());
        response.setEduReq(entity.getJobPostingInfoEntity().getEduReq());
        response.setExpLevel(entity.getJobPostingInfoEntity().getExpLevel());
        response.setSkills(entity.getJobPostingInfoEntity().getSkills());
        response.setLocation(entity.getJobPostingInfoEntity().getLocation());
        response.setWorkType(entity.getJobPostingInfoEntity().getWorkType());
        response.setWorkPlace(entity.getJobPostingInfoEntity().getWorkPlace());
        response.setSalary(entity.getJobPostingInfoEntity().getSalary());
        response.setPosition(entity.getJobPostingInfoEntity().getPosition());
        response.setCompanyName(entity.getCompany().getName());
        response.setCompanyEmail(entity.getCompany().getEmail());

        return response;
    }


}
