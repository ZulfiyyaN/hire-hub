package com.example.hirehub.repository;

import com.example.hirehub.model.entity.AdminEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    boolean existsByEmail(String email);
    Optional<AdminEntity> findByEmail(String email);
}
