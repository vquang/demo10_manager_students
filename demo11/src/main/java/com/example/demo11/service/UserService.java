package com.example.demo11.service;

import com.example.demo11.converter.ToDto;
import com.example.demo11.converter.ToEntity;
import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.request.UserRequest;
import com.example.demo11.dto.response.UserResponse;
import com.example.demo11.entity.UserEntity;
import com.example.demo11.jwt.JwtUtil;
import com.example.demo11.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse.usernameCurrent = username;
        UserEntity userEntity = userRepository.findByUsername(username);
        User user = new User(username, userEntity.getPassword(), new ArrayList<>());
        return (UserDetails) user;
    }
    @Autowired
    private ToDto toDto;
    @Autowired
    private ToEntity toEntity;
    public BaseResponse<Status, UserResponse> userShow() {
        String username = UserResponse.usernameCurrent;
        UserEntity userEntity = userRepository.findByUsername(username);
        UserResponse userResponse = toDto.userChange(userEntity);
        Status status = new Status("000", "show user successful!");
        return new BaseResponse<>(status, userResponse);
    }
    public BaseResponse<Status, UserResponse> userUpdate(UserRequest userRequest) {
        UserEntity userEntity = userRepository.findByUsername(userRequest.getUsername());
        userRequest.setId(userEntity.getId());
        userRequest.setUsername(userEntity.getUsername());
        userRequest.setPassword(userEntity.getPassword());
        userEntity = toEntity.userChange(userRequest);
        userRepository.save(userEntity);
        UserResponse userResponse = toDto.userChange(userEntity);
        Status status = new Status("000", "update user successful!");
        return new BaseResponse<>(status, userResponse);
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    public BaseResponse<Status, UserResponse> changePassword(UserRequest userRequest) {
        UserEntity userEntity = userRepository.findByUsername(userRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(userEntity);
        UserResponse userResponse = toDto.userChange(userEntity);
        Status status = new Status("000", "change password successful!");
        return new BaseResponse<>(status, userResponse);
    }
}























