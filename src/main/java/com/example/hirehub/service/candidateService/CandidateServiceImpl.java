package com.example.hirehub.service.candidateService;

import com.example.hirehub.exception.AlreadyExistsException;
import com.example.hirehub.mapper.CandidateMapper;
import com.example.hirehub.model.entity.CandidateEntity;
import com.example.hirehub.model.request.CandidateRegisterRequest;
import com.example.hirehub.model.response.CandidateRegisterResponse;
import com.example.hirehub.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CandidateServiceImpl implements CandidateService {
    CandidateRepository candidateRepository;
    CandidateMapper candidateMapper;


    @Override
    public CandidateRegisterResponse candidateRegister(CandidateRegisterRequest request) {
        if (candidateRepository.existsByEmail(request.getEmail())) {
            log.warn("Email {} already exists", request.getEmail());
            throw new AlreadyExistsException("Candidate with this email already exists");
        }
        if (candidateRepository.existsByPhone(request.getPhone())) {
            log.warn("Phone {} already exists", request.getPhone());
            throw new AlreadyExistsException("Candidate with this phone already exists");
        }

        CandidateEntity candidateEntity = candidateMapper.toCandidate(request);
        CandidateRegisterResponse response = candidateMapper.toResponse(candidateEntity);
        return response;

    }
}
