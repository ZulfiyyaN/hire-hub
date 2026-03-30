package com.example.hirehub.service.adminService;

import com.example.hirehub.exception.UserNotFoundException;
import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.repository.CandidateRepository;
import com.example.hirehub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminServiceImpl implements AdminService {
    UserRepository userRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public boolean changeStatus(String email, Status status) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            log.warn("Candidate not found with {} ", email);
            throw new UserNotFoundException("User not found!");
        }
        optionalUser.get().setStatus(status);
        userRepository.saveAndFlush(optionalUser.get());

        CandidateEntity entity = optionalUser.get().getCandidateEntity();

        if(entity!=null){
            entity.setUser(optionalUser.get());
            optionalUser.get().setCandidateEntity(entity);
//            entity.setStatus(status);
            candidateRepository.save(entity);
        }


        log.info("Status is changed to ACTIVE");
        return true;
    }
}
