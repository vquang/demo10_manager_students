package com.example.demo11.controller;

import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.response.StudentResponse;
import com.example.demo11.service.StudentService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GpaController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/gpa/show_gpa")
    public BaseResponse<Status, List<StudentResponse>> showGpa(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return studentService.showStudents(page, limit);
    }
    @PutMapping("/gpa/update_gpa/{id}")
    public BaseResponse<Status, StudentResponse> updateGpa(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody Map<String, Double> gpa
    ) {
        return studentService.updateGpa(id, gpa.get("gpa"), page, limit);
    }
    @GetMapping("/gpa/increase_gpa")
    public BaseResponse<Status, List<StudentResponse>> incGpa(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return studentService.incGpa(page, limit);
    }
    @GetMapping("/gpa/decrease_gpa")
    public BaseResponse<Status, List<StudentResponse>> decGpa(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return studentService.decGpa(page, limit);
    }
}




















