package com.example.hirehub.controller;

import com.example.hirehub.exception.AlreadyExistsException;
import com.example.hirehub.model.request.AuthRequest;
import com.example.hirehub.model.response.AuthResponse;
import com.example.hirehub.service.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login_admin")
    public ResponseEntity<AuthResponse> loginForAdmin(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.loginForAdmin(request);
        return ResponseEntity.ok(response);
    }
}
