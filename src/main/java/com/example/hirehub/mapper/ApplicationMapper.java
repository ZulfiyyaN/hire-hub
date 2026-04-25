package com.example.hirehub.mapper;

import com.example.hirehub.model.entity.ApplicationEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.enumeration.StatusApplication;
import com.example.hirehub.model.response.ApplicationForCandidateResponse;
import com.example.hirehub.model.response.ApplicationResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplicationMapper {


    public ApplicationResponse toResponse(ApplicationEntity entity) {
        CandidateEntity candidateEntity = entity.getCandidate();
        CandidateResponse candidateResponseRecord = new CandidateResponse(
                candidateEntity.getId(),
                candidateEntity.getName(),
                candidateEntity.getSurname(),
                candidateEntity.getEmail(),
                candidateEntity.getPhone(),
                candidateEntity.getGender().name(),
                candidateEntity.getCandidateInfo().getDateOfBirth(),
                candidateEntity.getCandidateInfo().getEducation(),
                candidateEntity.getCandidateInfo().getWorkExperience(),
                candidateEntity.getCandidateInfo().getKnowledge()
        );
        ApplicationResponse response = new ApplicationResponse();
        response.setId(entity.getId());
        response.setCandidate(candidateResponseRecord);
        response.setJobTitle(entity.getJobPosting().getJobTitle());
        response.setExpiredDate(entity.getJobPosting().getExpiredDate());
        response.setStatus(entity.getStatus());
        response.setApplicationDate(entity.getApplicationDate());

        return response;
    }


    public ApplicationForCandidateResponse toResponseForCandidate(ApplicationEntity entity){
        ApplicationForCandidateResponse response = new ApplicationForCandidateResponse();
        response.setId(entity.getId());
        response.setJobTitle(entity.getJobPosting().getJobTitle());
        response.setEduReq(entity.getJobPosting().getJobPostingInfoEntity().getEduReq());
        response.setExpLevel(entity.getJobPosting().getJobPostingInfoEntity().getExpLevel());
        response.setSkills(entity.getJobPosting().getJobPostingInfoEntity().getSkills());
        response.setLocation(entity.getJobPosting().getJobPostingInfoEntity().getLocation());
        response.setWorkType(entity.getJobPosting().getJobPostingInfoEntity().getWorkType());
        response.setWorkPlace(entity.getJobPosting().getJobPostingInfoEntity().getWorkPlace());
        response.setSalary(entity.getJobPosting().getJobPostingInfoEntity().getSalary());
        response.setPosition(entity.getJobPosting().getJobPostingInfoEntity().getPosition());
        response.setCompanyName(entity.getJobPosting().getCompany().getName());
        response.setCompanyEmail(entity.getJobPosting().getCompany().getEmail());
        response.setCreatedAtProfile(entity.getJobPosting().getCompany().getCreatedAt());
        response.setExpiredDate(entity.getJobPosting().getExpiredDate());
        response.setStatus(entity.getJobPosting().getStatus());
        response.setApplicationDate(entity.getApplicationDate());
        response.setStatusApplication(entity.getStatus());
        response.setDecisionDate(entity.getDecisionDate());
     return response;
    }








}


