package com.example.hirehub.repository;

import com.example.hirehub.model.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <CompanyEntity, Long>{
}
