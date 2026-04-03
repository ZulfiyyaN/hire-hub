package com.example.hirehub.repository;

import com.example.hirehub.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

   Optional<UserEntity> findByEmail(String email);


   @Query(value = "SELECT * FROM user_entity u WHERE u.id = :id", nativeQuery = true)
   Optional<UserEntity> findByIdNative(@Param("id") Long id);


//    Optional<UserEntity> findById(Long id);



}
