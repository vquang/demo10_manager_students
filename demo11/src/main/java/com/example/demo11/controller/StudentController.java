package com.example.demo11.controller;

import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.request.StudentRequest;
import com.example.demo11.dto.response.StudentResponse;
import com.example.demo11.repository.IStudentRepository;
import com.example.demo11.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/student/show_students")
    public BaseResponse<Status, List<StudentResponse>> showStudents(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit) {
        return studentService.showStudents(page, limit);
    }
    @PostMapping("/student/add_student")
    public BaseResponse<Status, StudentResponse> addStudent(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody StudentRequest studentRequest
    ) {
        return studentService.addStudent(studentRequest, page, limit);
    }
    @PutMapping("/student/update_student/{id}")
    public BaseResponse<Status, StudentResponse> updateStudent(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody StudentRequest studentRequest
            ) {
        return studentService.updateStudent(id, studentRequest, page, limit);
    }
    @DeleteMapping("/student/delete_student/{id}")
    public BaseResponse<Status, StudentResponse> deleteStudent(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return studentService.deleteStudent(id, page, limit);
    }
    @PostMapping("/student/search_students")
    public BaseResponse<Status, List<StudentResponse>> searchStudents(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody Map<String, String> search
            ) {
        return studentService.searchStudents(search.get("search"), page, limit);
    }
}
























