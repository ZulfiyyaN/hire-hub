package com.example.hirehub.service;

import com.example.hirehub.exception.IncorrectPasswordException;
import com.example.hirehub.model.entity.AdminEntity;
import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.request.AuthRequest;
import com.example.hirehub.model.response.AuthResponse;
import com.example.hirehub.repository.AdminRepository;
import com.example.hirehub.repository.UserRepository;
import com.example.hirehub.security.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    AdminRepository adminRepository;

    public AuthResponse login(AuthRequest request) {

        Optional<UserEntity> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        if (!passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())) {
            throw new IncorrectPasswordException("Password is not correct!");
        }
        String accessToken = "Bearer " + jwtService.generateAccessToken(optionalUser.get().getEmail());
        String refreshToken = "Bearer " + jwtService.generateRefreshToken(optionalUser.get().getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse loginForAdmin(AuthRequest request) {

        Optional<AdminEntity> optionalAdmin = adminRepository.findByEmail(request.getEmail());
        if (optionalAdmin.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        if (!passwordEncoder.matches(request.getPassword(), optionalAdmin.get().getPassword())) {
            throw new IncorrectPasswordException("Password is not correct!");
        }
        String accessToken = "Bearer " + jwtService.generateAccessToken(optionalAdmin.get().getEmail());
        String refreshToken = "Bearer " + jwtService.generateRefreshToken(optionalAdmin.get().getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }


}
