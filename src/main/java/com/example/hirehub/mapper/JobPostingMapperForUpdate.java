package com.example.hirehub.mapper;

import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingInfoEntity;
import com.example.hirehub.model.request.jobPostingRequest.JobPostingUpdateRequest;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingUpdateResponse;

public class JobPostingMapperForUpdate {

    public JobPostingEntity toEntityForUpdate(JobPostingUpdateRequest request){
        JobPostingEntity entity = new JobPostingEntity();
        entity.setJobTitle(request.getJobTitle());
        entity.setExpiredDate(request.getExpiredDate());

        JobPostingInfoEntity infoEntity = new JobPostingInfoEntity();
        infoEntity.setEduReq(request.getEduReq());
        infoEntity.setExpLevel(request.getExpLevel());
        infoEntity.setSkills(request.getSkills());
        infoEntity.setLocation(request.getLocation());
        infoEntity.setWorkType(request.getWorkType());
        infoEntity.setWorkPlace(request.getWorkPlace());
        infoEntity.setSalary(request.getSalary());
        infoEntity.setPosition(request.getPosition());

        entity.setJobPostingInfoEntity(infoEntity);
        infoEntity.setJobPostingEntity(entity);

        return entity;
    }

    public JobPostingUpdateResponse toResponseForUpdate(JobPostingEntity entity){
        JobPostingUpdateResponse response = new JobPostingUpdateResponse();

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

        return response;
    }







}
