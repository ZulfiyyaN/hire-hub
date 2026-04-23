package com.example.hirehub.repository;

import com.example.hirehub.model.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

    boolean existsByJobPosting_IdAndCandidate_Id(Long jobPostingId, Long candidateId);

    List<ApplicationEntity> findAll();

    public Optional<ApplicationEntity> findById(Long id);
}
