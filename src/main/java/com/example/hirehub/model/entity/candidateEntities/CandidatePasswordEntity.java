package com.example.hirehub.model.entity.candidateEntities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="candidate_passwords")
public class CandidatePasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String password;
    @OneToOne
    @JoinColumn(name = "profile_id")
    CandidateEntity candidate;

}
