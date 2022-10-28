package com.example.demo11.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birth")
    private Date birth;
    @Column(name = "address")
    private String address;
    @Column(name = "gpa")
    private double gpa;
    @ManyToOne
    @JoinColumn(name = "majorId")
    private MajorEntity majorEntity = new MajorEntity();

    public StudentEntity() {

    }

    public StudentEntity(Long id, String name, String gender, Date birth, String address, double gpa, MajorEntity majorEntity) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.gpa = (double) Math.round(gpa * 100) / 100;
        this.majorEntity = majorEntity;
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = (double) Math.round(gpa * 100) / 100;
    }

    public MajorEntity getMajorEntity() {
        return majorEntity;
    }

    public void setMajorEntity(MajorEntity majorEntity) {
        this.majorEntity = majorEntity;
    }
}
