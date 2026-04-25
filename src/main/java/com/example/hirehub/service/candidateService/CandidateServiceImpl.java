package com.example.hirehub.service.candidateService;

import com.example.hirehub.exception.*;
import com.example.hirehub.mapper.ApplicationMapper;
import com.example.hirehub.mapper.CandidateMapperForRegister;
import com.example.hirehub.mapper.CandidateMapperForUpdate;
import com.example.hirehub.model.entity.ApplicationEntity;
import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateInfoEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.enumeration.StatusApplication;
import com.example.hirehub.model.request.candidateRequest.CandidateRegisterRequest;
import com.example.hirehub.model.request.candidateRequest.CandidateUpdateRequest;
import com.example.hirehub.model.response.ApplicationForCandidateResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateRegisterResponse;
import com.example.hirehub.model.response.candidateResponse.CandidateUpdateResponse;
import com.example.hirehub.model.response.companyResponse.CompanyShortResponse;
import com.example.hirehub.model.response.jobPostingResponse.JobPostResponse;
import com.example.hirehub.repository.ApplicationRepository;
import com.example.hirehub.repository.CandidateRepository;
import com.example.hirehub.repository.JobPostingRepository;
import com.example.hirehub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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
    JobPostingRepository jobPostingRepository;
    ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

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
        if (Period.between(request.getDateOfBirth(), LocalDate.now()).getYears() < 16) {
            log.warn("Candidate age should be min 16");
            throw new IncorrectAgeException("Age should be minimum 16");
        }

        CandidateEntity candidateEntity = candidateMapper.toCandidate(request);

        UserEntity user = new UserEntity();

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


    @Override
    public boolean applyJob(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<CandidateEntity> optionalCandidate = candidateRepository.findByEmail(email);
        if (optionalCandidate.isEmpty()) {
            log.warn("Candidate not found!");
            throw new CandidateNotFoundException("Candidate not found!");
        }

        Optional<JobPostingEntity> jobPosting = jobPostingRepository.findByIdNative(id);
        if (jobPosting.isEmpty()) {
            log.warn("Job post not found with {} id! ", id);
            throw new JobPostingNotFoundException("Job post with " + id + " not found!");
        }

        if (applicationRepository.existsByJobPosting_IdAndCandidate_Id(
                jobPosting.get().getId(),
                optionalCandidate.get().getId())) {

            log.warn("This job posting is applied before by candidate with {} id ", optionalCandidate.get().getId());
            throw new AlreadyDoneBeforeException("Already applied!");
        }

        ApplicationEntity application = new ApplicationEntity();
        application.setCandidate(optionalCandidate.get());
        application.setJobPosting(jobPosting.get());
        application.setApplicationDate(LocalDateTime.now());
        application.setStatus(StatusApplication.APPLIED);

        applicationRepository.save(application);

        log.info("You successfully applied job post with {} ", id);
        return true;
    }


    @Override
    public List<ApplicationForCandidateResponse> getApplications(String email) {
        if (!candidateRepository.existsByEmail(email)) {
            log.warn("Candidate not found");
            throw new CandidateNotFoundException("Not found!");
        }
        Long id = candidateRepository.findByEmail(email).get().getId();
        List<ApplicationEntity> list = applicationRepository.findByCandidateId(id);

        if (list.isEmpty()) {
            log.warn("Candidate does not have application!");
            throw new CandidateNotFoundException("Not found!");
        }
        List<ApplicationForCandidateResponse> appList = list.stream()
                .map(a -> applicationMapper.toResponseForCandidate(a))
                .toList();
        return appList;
    }

}









