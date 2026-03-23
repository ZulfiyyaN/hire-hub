package com.example.hirehub.repository;

import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository <CompanyEntity, Long>{

    boolean existsByEmail(String email);
    Optional<CompanyEntity> findByEmail(String email);
}
