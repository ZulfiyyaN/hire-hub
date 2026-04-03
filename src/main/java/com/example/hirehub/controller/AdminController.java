package com.example.hirehub.controller;

import com.example.hirehub.model.entity.UserEntity;
import com.example.hirehub.model.enumeration.Status;
import com.example.hirehub.service.adminService.AdminService;
import com.example.hirehub.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminController {

    AdminService adminService;


    @PostMapping("/change_status")
    public ResponseEntity<?> make_Active(@RequestParam Long id, @RequestParam Status status){
        adminService.changeStatus(id, status);
        return ResponseEntity.accepted().build();
    }





}
