package com.nicogmerz4.portfolio.controller;

import com.nicogmerz4.portfolio.security.UserService;
import com.nicogmerz4.portfolio.service.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService service;
    
    @GetMapping
    public ResponseEntity getUser() {
        CustomResponse response = service.getUser();        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PostMapping("/profile")
    public ResponseEntity uploadProfile(@RequestParam("profile") MultipartFile file) {
        CustomResponse response = service.uploadProfile(file);
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PostMapping("/background")
    public ResponseEntity uploadBackground(@RequestParam("background") MultipartFile file) {
        CustomResponse response = service.uploadBackground(file);
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
}
