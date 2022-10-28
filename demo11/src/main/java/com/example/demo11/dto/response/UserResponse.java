package com.example.demo11.dto.response;

public class UserResponse {
    public static String usernameCurrent;
    private Long id;
    private String username;
    private String name;
    private String gender;
    private String address;

    public UserResponse() {
    }

    public UserResponse(Long id, String username, String name, String gender, String address) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
