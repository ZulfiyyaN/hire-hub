package com.example.hirehub.repository;

import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

   Optional<UserEntity> findByEmail(String email);


   @Query(value = "SELECT * FROM user_entity u WHERE u.id = :id", nativeQuery = true)
   Optional<UserEntity> findByIdNative(@Param("id") Long id);

   @Query(value = "SELECT * FROM user_entity WHERE status = :status", nativeQuery = true)
   List<UserEntity> findAllByStatus(@Param("status")String status);




}
