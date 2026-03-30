package com.example.hirehub.service.candidateService;

import com.example.hirehub.exception.AlreadyExistsException;
import com.example.hirehub.exception.CandidateNotFoundException;
import com.example.hirehub.mapper.CandidateMapperForRegister;
import com.example.hirehub.mapper.CandidateMapperForUpdate;
import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateInfoEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.request.candidateRequest.CandidateRegisterRequest;
import com.example.hirehub.model.request.candidateRequest.CandidateUpdateRequest;
import com.example.hirehub.model.response.candidateResponse.CandidateRegisterResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateUpdateResponse;
import com.example.hirehub.repository.CandidateRepository;
import com.example.hirehub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandidateServiceImpl implements CandidateService {
    CandidateRepository candidateRepository;
    CandidateMapperForRegister candidateMapper;
    CandidateMapperForUpdate candidateMapperForUpdate;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

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

        UserEntity user = new UserEntity();

        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CANDIDATE);
        user.setStatus(Status.ACTIVE);

        candidateEntity.setUser(user);
        user.setCandidateEntity(candidateEntity);
        userRepository.save(user);
        candidateRepository.save(candidateEntity);


        CandidateRegisterResponse response = candidateMapper.toResponse(candidateEntity);
        log.info("{} is registered", request.getName() + " " + request.getSurname());
        return response;
    }


    @Override
    public CandidateUpdateResponse candidateUpdate(String email, CandidateUpdateRequest request) {

        Optional<CandidateEntity> entity = candidateRepository.findByEmail(email);
        if (entity.isEmpty()) {
            log.warn("Candidate not found with {} ", email);
            throw new CandidateNotFoundException("Candidate not found: " + email);
        }
        if (request.getName() != null) {
            entity.get().setName(request.getName());
        }
        if (request.getSurname() != null) {
            entity.get().setSurname(request.getSurname());
        }
        if (request.getPhone() != null) {
            entity.get().setPhone(request.getPhone());
        }
        if (request.getGender() != null) {
            entity.get().setGender(request.getGender());
        }
        entity.get().setLastUpdate(LocalDateTime.now());

        CandidateInfoEntity info = entity.get().getCandidateInfo();

        if (request.getDateOfBirth() != null) {
            info.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getEducation() != null) {
            info.setEducation(request.getEducation());
        }
        if (request.getWorkExperience() != null) {
            info.setWorkExperience(request.getWorkExperience());
        }
        if (request.getKnowledge() != null) {
            info.setKnowledge(request.getKnowledge());
        }

        entity.get().setCandidateInfo(info);
        info.setCandidate(entity.get());

        CandidateUpdateResponse response = candidateMapperForUpdate.toResponseForUpdate(entity.get());
        return response;
    }

    @Override
    public boolean deleteProfilForCandidate(String email) {
        Optional<UserEntity> optionalCandidate = userRepository.findByEmail(email);
        if (optionalCandidate.isEmpty()) {
            log.warn("Candidate not found with {} ", email);
            throw new CandidateNotFoundException("Candidate not found: " + email);
        }
        optionalCandidate.get().setStatus(Status.DELETED);
        userRepository.save(optionalCandidate.get());
        return true;
    }


}
