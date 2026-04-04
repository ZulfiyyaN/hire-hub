package com.example.hirehub.service.adminService;


import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.enumeration.StatusJobPost;

public interface AdminService {

    boolean changeStatus(Long id, Status status);

    boolean changeStatusJobPost(Long id, StatusJobPost status);

//    List<CandidateEntity> getAllPendingCandidates(); // Yalnız gözləmədə olanlar
//    void blockUser(String email);
}
