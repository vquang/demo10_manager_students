package com.example.demo11.controller;

import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.request.UserRequest;
import com.example.demo11.dto.response.UserResponse;
import com.example.demo11.jwt.JwtUtil;
import com.example.demo11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/user/login")
    public BaseResponse<Status, String> userLogin(@RequestBody UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getUsername(),
                        userRequest.getPassword()
                )
        );
        UserDetails userDetails = userService.loadUserByUsername(userRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        Status status = new Status("000", "created jwt successful!");
        return new BaseResponse<>(status, jwt);
    }

    @GetMapping("/user/show")
    public BaseResponse<Status, UserResponse> userShow() {
        return userService.userShow();
    }

    @PutMapping("/user/update")
    public BaseResponse<Status, UserResponse> userUpdate(@RequestBody UserRequest userRequest) {
        return userService.userUpdate(userRequest);
    }

    @PutMapping("/user/change_password")
    public BaseResponse<Status, UserResponse> userChangePassword(@RequestBody UserRequest userRequest) {
        return userService.changePassword(userRequest);
    }
    @PostMapping("/user/check")
    public Status check() {
        return new Status("000", "authentication successful!");
    }
}



















