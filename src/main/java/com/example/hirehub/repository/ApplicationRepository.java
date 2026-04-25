package com.example.hirehub.repository;

import com.example.hirehub.model.entity.ApplicationEntity;
import com.example.hirehub.model.enumeration.StatusApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

    boolean existsByJobPosting_IdAndCandidate_Id(Long jobPostingId, Long candidateId);

    List<ApplicationEntity> findByCandidateId(Long id);






}
