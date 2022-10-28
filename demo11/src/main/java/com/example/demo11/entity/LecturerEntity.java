package com.example.demo11.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Lecturers")
public class LecturerEntity {
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
    @Column(name = "level")
    private String level;
    @ManyToOne
    @JoinColumn(name = "majorId")
    private MajorEntity majorEntity = new MajorEntity();

    public LecturerEntity() {
    }

    public LecturerEntity(Long id, String name, String gender, Date birth, String address, String level, MajorEntity majorEntity) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.level = level;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public MajorEntity getMajorEntity() {
        return majorEntity;
    }

    public void setMajorEntity(MajorEntity majorEntity) {
        this.majorEntity = majorEntity;
    }
}
























