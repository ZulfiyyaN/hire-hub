package com.example.hirehub.service.adminService;


import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.entity.candidateEntities.CandidateEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.enumeration.StatusJobPost;
import com.example.hirehub.model.response.UserResponse;

import java.util.List;

public interface AdminService {

    boolean changeStatus(Long id, Status status);

    boolean changeStatusJobPost(Long id, StatusJobPost status);

    List<UserResponse> getAllByStatus(Status status);

//    void blockUser(String email);
}
