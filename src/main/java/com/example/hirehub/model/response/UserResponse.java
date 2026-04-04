package com.example.hirehub.model.response;

import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.enumeration.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String email;
    Role role;
    Status status;

}
