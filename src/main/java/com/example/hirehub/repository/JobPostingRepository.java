package com.example.hirehub.repository;

import com.example.hirehub.model.entity.jobPostingEntities.JobPostingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPostingEntity, Long> {

}
