package com.example.hirehub.mapper;

import com.example.hirehub.exception.CandidateNotFoundException;
import com.example.hirehub.model.entity.CandidateEntity;
import com.example.hirehub.model.entity.CandidateInfoEntity;
import com.example.hirehub.model.request.CandidateUpdateRequest;
import com.example.hirehub.model.response.CandidateUpdateResponse;
import com.example.hirehub.repository.CandidateRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = true)
public class CandidateMapperForUpdate {

    CandidateRepository candidateRepository;

    public CandidateUpdateResponse toResponseForUpdate(CandidateEntity entity) {
        CandidateInfoEntity info = entity.getCandidateInfo();
        CandidateUpdateResponse response = new CandidateUpdateResponse();

        response.setName(entity.getName());
        response.setSurname(entity.getSurname());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setGender(entity.getGender());

        response.setDateOfBirth(info.getDateOfBirth());
        response.setEducation(info.getEducation());
        response.setWorkExperience(info.getWorkExperience());
        response.setKnowledge(info.getKnowledge());

        return response;
        }


    }

