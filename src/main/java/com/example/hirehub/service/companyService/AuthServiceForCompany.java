package com.example.hirehub.service.companyService;

import com.example.hirehub.exception.IncorrectPasswordException;
import com.example.hirehub.model.entity.candidateEntities.CandidatePasswordEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyPasswordEntity;
import com.example.hirehub.model.request.companyRequest.CompanyLoginRequest;
import com.example.hirehub.model.response.AuthResponse;
import com.example.hirehub.repository.CompanyRepository;
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
public class AuthServiceForCompany {
    JwtService jwtService;
    PasswordEncoder passwordEncoder;
    CompanyRepository companyRepository;


    public AuthResponse login(CompanyLoginRequest request) {
        Optional<CompanyEntity> optionalCompanyEntity = companyRepository.findByEmail(request.getEmail());
        if (optionalCompanyEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        CompanyPasswordEntity passwordEntity = optionalCompanyEntity.get().getCompanyPasswordEntity();
        if (!passwordEncoder.matches(request.getPassword(), passwordEntity.getPassword())) {
            throw new IncorrectPasswordException("Password is not correct!");
        }
        String accessToken = "Bearer " + jwtService.generateAccessToken(passwordEntity.getCompany().getEmail());
        String refreshToken = "Bearer " + jwtService.generateRefreshToken(passwordEntity.getCompany().getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

}
