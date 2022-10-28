package com.example.demo11.converter;

import com.example.demo11.dto.response.LecturerResponse;
import com.example.demo11.dto.response.StudentResponse;
import com.example.demo11.dto.response.UserResponse;
import com.example.demo11.entity.LecturerEntity;
import com.example.demo11.entity.StudentEntity;
import com.example.demo11.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ToDto {
    public UserResponse userChange(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getName(),
                userEntity.getGender(),
                userEntity.getAddress()
        );
    }
    public StudentResponse studentChange(StudentEntity studentEntity, int totalPages, int page) {
        return new StudentResponse(
                studentEntity.getId(),
                studentEntity.getName(),
                studentEntity.getGender(),
                studentEntity.getBirth(),
                studentEntity.getAddress(),
                studentEntity.getGpa(),
                studentEntity.getMajorEntity().getName(),
                totalPages,
                page
        );
    }
    public LecturerResponse lecturerChange(LecturerEntity lecturerEntity, int totalPages, int page) {
        return new LecturerResponse(
                lecturerEntity.getId(),
                lecturerEntity.getName(),
                lecturerEntity.getGender(),
                lecturerEntity.getBirth(),
                lecturerEntity.getAddress(),
                lecturerEntity.getLevel(),
                lecturerEntity.getMajorEntity().getName(),
                totalPages,
                page
        );
    }
}
























