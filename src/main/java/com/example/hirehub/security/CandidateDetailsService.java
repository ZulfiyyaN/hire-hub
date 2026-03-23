package com.example.hirehub.security;

import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.repository.CandidateRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CandidateDetailsService implements UserDetailsService {

    CandidateRepository candidateRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<CandidateEntity> optionalcandidate = candidateRepository.findByEmail(email);
        if (optionalcandidate.isPresent()) {
            log.info("Candidate is found with {}", email);
            return new CandidateSecurity(optionalcandidate.get());
        } else {
            log.warn("Username is not found with {}", email);
            throw new UsernameNotFoundException("User not found");
        }
    }
}
