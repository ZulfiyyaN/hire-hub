package com.example.hirehub.service.adminService;


import com.example.hirehub.model.enumeration.Status;

public interface AdminService {

    boolean changeStatus(Long id, Status status);
//    void makeBlockedCandidate(Long id, String reason);
//    List<CandidateEntity> getAllPendingCandidates(); // Yalnız gözləmədə olanlar
//    void blockUser(String email);
}
