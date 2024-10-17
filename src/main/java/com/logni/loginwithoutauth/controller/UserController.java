package com.logni.loginwithoutauth.controller;

import com.logni.loginwithoutauth.dto.ReqRes;
import com.logni.loginwithoutauth.service.UserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserServiceInterface service;
    public UserController(UserServiceInterface service){
        this.service=service;
    }
    @GetMapping("/all")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }
    @PostMapping("/create/user")
    public ResponseEntity<ReqRes> createNewUser(@RequestBody ReqRes userDetails){
        return ResponseEntity.ok(service.createNewUser(userDetails));
    }
    @PutMapping("/update/user")
    public ResponseEntity<ReqRes> updateUser(@RequestBody ReqRes userDetails){
        return ResponseEntity.ok(service.updateUser(userDetails));
    }
    @PostMapping("/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes userDetails){
        return ResponseEntity.ok(service.login(userDetails));
    }

}
