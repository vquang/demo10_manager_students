package com.example.demo11.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Majors")
public class MajorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "majorEntity")
    private List<StudentEntity> studentEntities = new ArrayList<>();
    @OneToMany(mappedBy = "majorEntity")
    private List<LecturerEntity> lecturerEntities = new ArrayList<>();

    public MajorEntity() {
    }

    public MajorEntity(Long id, String name, List<StudentEntity> studentEntities, List<LecturerEntity> lecturerEntities) {
        this.id = id;
        this.name = name;
        this.studentEntities = studentEntities;
        this.lecturerEntities = lecturerEntities;
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

    public List<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(List<StudentEntity> studentEntities) {
        this.studentEntities = studentEntities;
    }

    public List<LecturerEntity> getLecturerEntities() {
        return lecturerEntities;
    }

    public void setLecturerEntities(List<LecturerEntity> lecturerEntities) {
        this.lecturerEntities = lecturerEntities;
    }
}
