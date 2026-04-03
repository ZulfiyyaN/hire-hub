package com.example.hirehub.repository;

import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPostingEntity, Long> {

    @Query(value = "SELECT * FROM job_postings jp WHERE jp.id = :id AND jp.status IN('PENDING', 'ACTIVE') ", nativeQuery = true)
    Optional<JobPostingEntity> findByIdNative(@Param("id") Long id);


}
