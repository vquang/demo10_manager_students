package com.example.demo11.converter;

import com.example.demo11.dto.request.LecturerRequest;
import com.example.demo11.dto.request.StudentRequest;
import com.example.demo11.dto.request.UserRequest;
import com.example.demo11.dto.response.UserResponse;
import com.example.demo11.entity.LecturerEntity;
import com.example.demo11.entity.StudentEntity;
import com.example.demo11.entity.UserEntity;
import com.example.demo11.repository.IMajorRepository;
import com.example.demo11.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToEntity {
    public UserEntity userChange(UserRequest userRequest) {
        return new UserEntity(
                userRequest.getId(),
                userRequest.getUsername(),
                userRequest.getPassword(),
                userRequest.getName(),
                userRequest.getGender(),
                userRequest.getAddress()
        );
    }
    @Autowired
    private IMajorRepository majorRepository;
    public StudentEntity studentChange(StudentRequest studentRequest) {
        return new StudentEntity(
                studentRequest.getId(),
                studentRequest.getName(),
                studentRequest.getGender(),
                studentRequest.getBirth(),
                studentRequest.getAddress(),
                studentRequest.getGpa(),
                majorRepository.findByName(studentRequest.getMajor())
        );
    }
    public LecturerEntity lecturerChange(LecturerRequest lecturerRequest) {
        return new LecturerEntity(
                lecturerRequest.getId(),
                lecturerRequest.getName(),
                lecturerRequest.getGender(),
                lecturerRequest.getBirth(),
                lecturerRequest.getAddress(),
                lecturerRequest.getLevel(),
                majorRepository.findByName(lecturerRequest.getMajor())
        );
    }
}



























