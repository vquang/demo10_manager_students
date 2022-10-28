package com.example.demo11.controller;

import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.request.LecturerRequest;
import com.example.demo11.dto.request.StudentRequest;
import com.example.demo11.dto.response.LecturerResponse;
import com.example.demo11.dto.response.StudentResponse;
import com.example.demo11.service.LecturerService;
import com.example.demo11.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;
    @GetMapping("/lecturer/show_lecturers")
    public BaseResponse<Status, List<LecturerResponse>> showLecturers(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit) {
        return lecturerService.showLecturers(page, limit);
    }
    @PostMapping("/lecturer/add_lecturer")
    public BaseResponse<Status, LecturerResponse> addLecturer(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody LecturerRequest lecturerRequest
    ) {
        return lecturerService.addLecturer(lecturerRequest, page, limit);
    }
    @PutMapping("/lecturer/update_lecturer/{id}")
    public BaseResponse<Status, LecturerResponse> updateLecturer(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody LecturerRequest lecturerRequest
    ) {
        return lecturerService.updateLecturer(id, lecturerRequest, page, limit);
    }
    @DeleteMapping("/lecturer/delete_lecturer/{id}")
    public BaseResponse<Status, LecturerResponse> deleteLecturer(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return lecturerService.deleteLecturer(id, page, limit);
    }
    @PostMapping("/lecturer/search_lecturers")
    public BaseResponse<Status, List<LecturerResponse>> searchLecturers(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody Map<String, String> search
    ) {
        return lecturerService.searchLecturers(search.get("search"), page, limit);
    }
}
