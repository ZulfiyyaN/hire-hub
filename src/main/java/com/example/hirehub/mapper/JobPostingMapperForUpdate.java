package com.example.hirehub.mapper;

import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingInfoEntity;
import com.example.hirehub.model.response.jobPostingResponse.JobPostingUpdateResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class JobPostingMapperForUpdate {

    public JobPostingUpdateResponse toResponseForUpdate(JobPostingEntity entity) {
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
