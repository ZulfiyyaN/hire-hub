package com.example.hirehub.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
@Entity
@Table(name = "candidates_info")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate dateOfBirth;
    String education;
    String workExperience;
    String knowledge;
    @OneToOne
    @JoinColumn(name = "candidate_id")
    CandidateEntity candidate;

}
