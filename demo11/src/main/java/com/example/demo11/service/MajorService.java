package com.example.demo11.service;

import com.example.demo11.converter.ToDto;
import com.example.demo11.converter.ToEntity;
import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.response.LecturerResponse;
import com.example.demo11.dto.response.StudentResponse;
import com.example.demo11.entity.LecturerEntity;
import com.example.demo11.entity.MajorEntity;
import com.example.demo11.entity.StudentEntity;
import com.example.demo11.repository.ILecturerRepository;
import com.example.demo11.repository.IMajorRepository;
import com.example.demo11.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorService {
    @Autowired
    private IMajorRepository majorRepository;
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private ILecturerRepository lecturerRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private ToDto toDto;
    @Autowired
    private ToEntity toEntity;
    public BaseResponse<Status, List<StudentResponse>> showStudents(String major, int page, int limit) {
        int totalPage = (int)Math.ceil((double)studentRepository.count() / limit);
        MajorEntity majorEntity = majorRepository.findByName(major);
        List<StudentEntity> studentEntities = majorEntity.getStudentEntities();
        totalPage = (int)Math.ceil((double)studentEntities.size() / limit);
        List<StudentResponse> studentResponses = new ArrayList<>();
        for(StudentEntity studentEntity: studentEntities) {
            studentResponses.add(toDto.studentChange(studentEntity, totalPage, page));
        }
        studentResponses = studentService.studentPage(studentResponses, page, limit);
        Status status = new Status("000", "show students successful!");
        return new BaseResponse<>(status, studentResponses);
    }
    public BaseResponse<Status, List<LecturerResponse>> showLecturers(String major, int page, int limit) {
        int totalPage = (int)Math.ceil((double)lecturerRepository.count() / limit);
        MajorEntity majorEntity = majorRepository.findByName(major);
        List<LecturerEntity> lecturerEntities = majorEntity.getLecturerEntities();
        totalPage = (int)Math.ceil((double)lecturerEntities.size() / limit);
        List<LecturerResponse> lecturerResponses = new ArrayList<>();
        for(LecturerEntity lecturerEntity: lecturerEntities) {
            lecturerResponses.add(toDto.lecturerChange(lecturerEntity, totalPage, page));
        }
        lecturerResponses = lecturerService.lecturerPage(lecturerResponses, page, limit);
        Status status = new Status("000", "show lecturers successful!");
        return new BaseResponse<>(status, lecturerResponses);
    }
    public BaseResponse<Status, List<Integer>> chartMajorStudent() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 3; ++i) {
            list.add(0);
        }
        for(StudentEntity studentEntity: studentEntities) {
            String major = studentEntity.getMajorEntity().getName();
            if(major.equals("Công Nghệ Thông Tin")) {
                list.set(0, list.get(0) + 1);
            } else if(major.equals("Kinh Tế")) {
                list.set(1, list.get(1) + 1);
            } else {
                list.set(2, list.get(2) + 1);
            }
        }
        Status status = new Status("000", "show chart major successful!");
        return new BaseResponse<>(status, list);
    }
    public BaseResponse<Status, List<Integer>> chartMajorLecturer() {
        List<LecturerEntity> lecturerEntities = lecturerRepository.findAll();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 3; ++i) {
            list.add(0);
        }
        for(LecturerEntity lecturerEntity: lecturerEntities) {
            String major = lecturerEntity.getMajorEntity().getName();
            if(major.equals("Công Nghệ Thông Tin")) {
                list.set(0, list.get(0) + 1);
            } else if(major.equals("Kinh Tế")) {
                list.set(1, list.get(1) + 1);
            } else {
                list.set(2, list.get(2) + 1);
            }
        }
        Status status = new Status("000", "show chart major successful!");
        return new BaseResponse<>(status, list);
    }
}






















