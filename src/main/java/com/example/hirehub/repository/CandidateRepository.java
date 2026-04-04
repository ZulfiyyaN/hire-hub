package com.example.hirehub.repository;

import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<CandidateEntity> findByEmail(String email);

    List<CandidateEntity> findAll();
}

