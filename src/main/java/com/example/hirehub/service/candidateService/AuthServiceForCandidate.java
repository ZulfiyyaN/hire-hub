package com.example.hirehub.service.candidateService;

import com.example.hirehub.exception.IncorrectPasswordException;
import com.example.hirehub.model.entity.CandidateEntity;
import com.example.hirehub.model.entity.CandidatePasswordEntity;
import com.example.hirehub.model.request.CandidateLoginRequest;
import com.example.hirehub.model.response.AuthResponse;
import com.example.hirehub.repository.CandidateRepository;
import com.example.hirehub.security.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthService {
    JwtService jwtService;
    PasswordEncoder passwordEncoder;
    CandidateRepository candidateRepository;


    public AuthResponse login(CandidateLoginRequest request) {
        Optional<CandidateEntity> optionalCandidateEntity = candidateRepository.findByEmail(request.getEmail());
        if (optionalCandidateEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        CandidatePasswordEntity passwordEntity = optionalCandidateEntity.get().getPasswordEntity();
        if (!passwordEncoder.matches(request.getPassword(), passwordEntity.getPassword())) {
            throw new IncorrectPasswordException("Password is not correct!");
        }
        String accessToken = "Bearer " + jwtService.generateAccessToken(passwordEntity.getCandidate().getEmail());
        String refreshToken = "Bearer " + jwtService.generateRefreshToken(passwordEntity.getCandidate().getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

}
