package com.example.hirehub.security;

import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandidateSecurity implements UserDetails {

    CandidateEntity candidate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return candidate.getPasswordEntity().getPassword();
    }

    @Override
    public String getUsername() {
        return candidate.getEmail();
    }
}
