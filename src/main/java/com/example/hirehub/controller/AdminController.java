package com.example.hirehub.controller;

import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.enumeration.Role;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.model.enumeration.StatusJobPost;
import com.example.hirehub.model.response.UserResponse;
import com.example.hirehub.service.adminService.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminController {

    AdminService adminService;


    @PutMapping("/change_status/{}")
    public ResponseEntity<?> changeStatusUser(@PathVariable Long id,
                                              @RequestParam Status status) {
        adminService.changeStatus(id, status);
        return ResponseEntity.accepted().build();
    }


    @PutMapping("/change_status_job_post/{jobPostId}")
    public ResponseEntity<?> changeStatusJobPost(@PathVariable Long jobPostId,
                                                 @RequestParam StatusJobPost status) {
        adminService.changeStatusJobPost(jobPostId, status);
        return ResponseEntity.accepted().build();
    }


    @GetMapping("/all_users_by_status")
    public ResponseEntity<List<UserResponse>> getAllUsersByStatus(@RequestParam Status status) {
        List<UserResponse> users = adminService.getAllByStatus(status);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/all_users_by_role")
    public ResponseEntity<List<UserResponse>> getAllUsersByRole(@RequestParam Role role) {
        List<UserResponse> response = adminService.getAllByRole(role);
        return ResponseEntity.ok(response);
    }


}
