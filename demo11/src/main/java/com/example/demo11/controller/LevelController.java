package com.example.demo11.controller;

import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.response.LecturerResponse;
import com.example.demo11.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LevelController {
    @Autowired
    private LecturerService lecturerService;
    @PostMapping("/level/show_level")
    public BaseResponse<Status, List<LecturerResponse>> showLevel(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody Map<String, String> level
    ) {
        return lecturerService.showLevel(level.get("level"), page, limit);
    }
    @PutMapping("/level/update_level/{id}")
    public BaseResponse<Status, LecturerResponse> updateLevel(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestBody Map<String, String> level
    ) {
        return lecturerService.updateLevel(id, level.get("level"), page, limit);
    }
}
























