package com.example.demo11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/student")
    public String student() {
        return "student";
    }
    @GetMapping("/lecturer")
    public String lecturer() {
        return "lecturer";
    }
    @GetMapping("/major_student")
    public String majorStudent() {
        return "major_student";
    }
    @GetMapping("/major_lecturer")
    public String majorLecturer() {
        return "major_lecturer";
    }
    @GetMapping("/level")
    public String level() {
        return "level";
    }
    @GetMapping("/gpa")
    public String gpa() {
        return "gpa";
    }
}





















