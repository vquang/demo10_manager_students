package com.example.demo11.dto.response;

import java.sql.Date;

public class StudentResponse {
    private Long id;
    private String name;
    private String gender;
    private Date birth;
    private String address;
    private Double gpa;
    private String major;
    private int totalPages;
    private int page;

    public StudentResponse() {
    }

    public StudentResponse(Long id, String name, String gender, Date birth, String address, Double gpa, String major, int totalPages, int page) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.gpa = gpa;
        this.major = major;
        this.totalPages = totalPages;
        this.page = page;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}

