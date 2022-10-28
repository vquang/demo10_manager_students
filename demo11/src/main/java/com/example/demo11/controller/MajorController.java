package com.example.demo11.controller;

import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.response.LecturerResponse;
import com.example.demo11.dto.response.StudentResponse;
import com.example.demo11.repository.IMajorRepository;
import com.example.demo11.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MajorController {
    @Autowired
    private MajorService majorService;
    @PostMapping("/major/show_students")
    public BaseResponse<Status, List<StudentResponse>> showStudents(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody Map<String, String> major
            ) {
        return majorService.showStudents(major.get("major"), page, limit);
    }
    @PostMapping("/major/show_lecturers")
    public BaseResponse<Status, List<LecturerResponse>> showLecturers(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody Map<String, String> major
    ) {
        return majorService.showLecturers(major.get("major"), page, limit);
    }
}





















