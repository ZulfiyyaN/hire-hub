package com.example.hirehub.repository;

import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.entity.companyEntities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository <CompanyEntity, Long>{

    boolean existsByEmail(String email);
    Optional<CompanyEntity> findByEmail(String email);

    @Query("SELECT c FROM CompanyEntity c JOIN FETCH c.user u WHERE c.email = :email")
    Optional<CompanyEntity> findByEmailWithUser(@Param("email") String email);





}