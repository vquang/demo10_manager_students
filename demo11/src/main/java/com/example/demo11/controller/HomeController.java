package com.example.demo11.controller;

import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.service.LecturerService;
import com.example.demo11.service.MajorService;
import com.example.demo11.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private MajorService majorService;
    @GetMapping("/home/chart_gpa")
    public BaseResponse<Status, List<Integer>> chartGpa() {
        return studentService.chartGpa();
    }
    @GetMapping("/home/chart_level")
    public BaseResponse<Status, List<Integer>> chartLevel() {
        return lecturerService.chartLevel();
    }
    @GetMapping("/home/chart_major_student")
    public BaseResponse<Status, List<Integer>> chartMajorStudent() {
        return majorService.chartMajorStudent();
    }
    @GetMapping("/home/chart_major_lecturer")
    public BaseResponse<Status, List<Integer>> chartMajorLecturer() {
        return majorService.chartMajorLecturer();
    }
}

























