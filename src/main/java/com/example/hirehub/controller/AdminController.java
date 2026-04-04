package com.example.hirehub.controller;

import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.enumeration.StatusJobPost;
import com.example.hirehub.service.adminService.AdminService;
import com.example.hirehub.service.AuthService;
import com.example.hirehub.service.jobPostingService.JobPostingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminController {

    AdminService adminService;


    @PutMapping("/change_status")
    public ResponseEntity<?> make_Active(@RequestParam Long id,
                                         @RequestParam Status status) {
        adminService.changeStatus(id, status);
        return ResponseEntity.accepted().build();
    }


    @PutMapping("/change_status_job_post/{jobPostId}")
    public ResponseEntity<?> changeStatus(@PathVariable Long jobPostId,
                                          @RequestParam StatusJobPost status) {
        adminService.changeStatusJobPost(jobPostId, status);
        return ResponseEntity.accepted().build();
    }


}
