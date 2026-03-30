package com.example.hirehub.config;

import com.example.hirehub.model.entity.AdminEntity;
import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.repository.AdminRepository;
import com.example.hirehub.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminInitializer {
    AdminRepository adminRepository;
    PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdmin() {
        return args -> {
            if (!adminRepository.existsByEmail("admin@example.com")) {
                AdminEntity admin = new AdminEntity();
                admin.setName("Super");
                admin.setSurname("Admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("Admin123"));
                admin.setRole(Role.ADMIN);

                adminRepository.save(admin);
                System.out.println("Admin created!");
            }
        };
    }
}
