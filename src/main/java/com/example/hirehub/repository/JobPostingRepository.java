package com.example.hirehub.repository;

import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import com.example.hirehub.model.response.jobPostingResponse.JobPostResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPostingEntity, Long> {

    @Query(value = "SELECT * FROM job_postings jp WHERE jp.id = :id AND jp.status IN('PENDING', 'ACTIVE') ", nativeQuery = true)
    Optional<JobPostingEntity> findByIdNative(@Param("id") Long id);

    @Query(value = "SELECT * FROM job_postings jp WHERE jp.id = :id AND jp.status IN('PENDING', 'ACTIVE', 'DELETED', 'EXPIRED') ", nativeQuery = true)
    Optional<JobPostingEntity> findByIdNativeAll(@Param("id") Long id);

    List<JobPostingEntity> findAll();


    @Modifying
    @Transactional
    @Query("UPDATE JobPostingEntity j SET j.status = 'EXPIRED' WHERE j.expiredDate < :now AND j.status = 'ACTIVE'")
    void updateExpiredJobs(LocalDateTime now);


}
