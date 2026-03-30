package com.example.hirehub.mapper;

import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateInfoEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidatePasswordEntity;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.request.candidateRequest.CandidateRegisterRequest;
import com.example.hirehub.model.response.candidateResponse.CandidateRegisterResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class CandidateMapperForRegister {
    PasswordEncoder passwordEncoder;

    public CandidateEntity toCandidate(CandidateRegisterRequest request) {

        if (request == null) {
            log.warn("Request can not be null!");
            throw new NullPointerException("Request is null!");
        }
        CandidateEntity entity = new CandidateEntity();
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setGender(request.getGender());
       // entity.setStatus(request.getStatus());

        CandidatePasswordEntity passwordEntity = new CandidatePasswordEntity();
        passwordEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        CandidateInfoEntity candidateInfo = new CandidateInfoEntity();
        candidateInfo.setDateOfBirth(request.getDateOfBirth());
        candidateInfo.setEducation(request.getEducation());
        candidateInfo.setWorkExperience(request.getWorkExperience());
        candidateInfo.setKnowledge(request.getKnowledge());

        entity.setCandidateInfo(candidateInfo);
        entity.setPasswordEntity(passwordEntity);
        candidateInfo.setCandidate(entity);
        passwordEntity.setCandidate(entity);

        return entity;
    }


    public CandidateRegisterResponse toResponse(CandidateEntity entity) {
        CandidateRegisterResponse response = new CandidateRegisterResponse();
        if (entity == null) {
            log.warn("Entity is null");
            throw new NullPointerException("Entity is null!");
        }
        response.setName(entity.getName());
        response.setSurname(entity.getSurname());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setGender(entity.getGender());

        return response;
    }





}
