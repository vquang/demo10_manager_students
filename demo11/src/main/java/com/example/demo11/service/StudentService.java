package com.example.demo11.service;

import com.example.demo11.converter.ToDto;
import com.example.demo11.converter.ToEntity;
import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.request.StudentRequest;
import com.example.demo11.dto.response.StudentResponse;
import com.example.demo11.entity.StudentEntity;
import com.example.demo11.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private ToEntity toEntity;
    @Autowired
    private ToDto toDto;
    public List<StudentResponse> studentPage(List<StudentResponse> studentResponses, int page, int limit) {
        List<StudentResponse> studentResponseList = new ArrayList<>();
        int start = limit * (page - 1);
        int end = start + limit - 1;
        for(int i = start; i <= end && i < studentResponses.size(); ++i) {
            studentResponseList.add(studentResponses.get(i));
        }
        return studentResponseList;
    }
    public BaseResponse<Status, List<StudentResponse>> showStudents(int page, int limit) {
        int totalPages = (int)Math.ceil((double)studentRepository.count() / limit);
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<StudentResponse> studentResponses = new ArrayList<>();
        for(StudentEntity studentEntity: studentEntities) {
            studentResponses.add(toDto.studentChange(studentEntity, totalPages, page));
        }
        studentResponses = studentPage(studentResponses, page, limit);
        Status status = new Status("000", "show students successful!");
        return new BaseResponse<>(status, studentResponses);
    }
    public BaseResponse<Status, StudentResponse> addStudent(StudentRequest studentRequest, int page, int limit) {
        int totalPage = (int)Math.ceil((double)studentRepository.count() / limit);
        StudentEntity studentEntity = toEntity.studentChange(studentRequest);
        studentRepository.save(studentEntity);
        StudentResponse studentResponse = toDto.studentChange(studentEntity, totalPage, page);
        Status status = new Status("000", "add student successful!");
        return new BaseResponse<>(status, studentResponse);
    }
    public BaseResponse<Status, StudentResponse> updateStudent(Long id, StudentRequest studentRequest, int page, int limit) {
        int totalPage = (int)Math.ceil((double)studentRepository.count() / limit);
        studentRequest.setId(id);
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        studentEntity = toEntity.studentChange(studentRequest);
        studentRepository.save(studentEntity);
        StudentResponse studentResponse = toDto.studentChange(studentEntity, totalPage, page);
        Status status = new Status("000", "update student successful!");
        return new BaseResponse<>(status, studentResponse);
    }
    public BaseResponse<Status, StudentResponse> deleteStudent(Long id, int page, int limit) {
        int totalPage = (int)Math.ceil((double)studentRepository.count() / limit);
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        StudentResponse studentResponse = toDto.studentChange(studentEntity, totalPage, page);
        studentRepository.delete(studentEntity);
        Status status = new Status("000", "delete student successful!");
        return new BaseResponse<>(status, studentResponse);
    }
    public BaseResponse<Status, List<StudentResponse>> searchStudents(String search, int page, int limit) {
        int totalPage = (int)Math.ceil((double)studentRepository.count() / limit);
        search = "%" + search + "%";
        List<StudentEntity> studentEntities = studentRepository.findByNameLike(search);
        totalPage = (int)Math.ceil((double)studentEntities.size() / limit);
        List<StudentResponse> studentResponses = new ArrayList<>();
        for(StudentEntity studentEntity: studentEntities) {
            studentResponses.add(toDto.studentChange(studentEntity, totalPage, page));
        }
        studentResponses = studentPage(studentResponses, page, limit);
        Status status = new Status("000", "search students successful!");
        return new BaseResponse<>(status, studentResponses);
    }
    public BaseResponse<Status, StudentResponse> updateGpa(Long id, double gpa, int page, int limit) {
        int totalPage = (int)Math.ceil((double)studentRepository.count() / limit);
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        studentEntity.setGpa(gpa);
        studentRepository.save(studentEntity);
        StudentResponse studentResponse = toDto.studentChange(studentEntity, totalPage, page);
        Status status = new Status("000", "update gpa successful!");
        return new BaseResponse<>(status, studentResponse);
    }
    public BaseResponse<Status, List<StudentResponse>> incGpa(int page, int limit) {
        int totalPage = (int)Math.ceil((double)studentRepository.count() / limit);
        List<StudentEntity> studentEntities = studentRepository.findAllByOrderByGpaAsc();
        List<StudentResponse> studentResponses = new ArrayList<>();
        for(StudentEntity studentEntity: studentEntities) {
            studentResponses.add(toDto.studentChange(studentEntity, totalPage, page));
        }
        studentResponses = studentPage(studentResponses, page, limit);
        Status status = new Status("000", "increase students successful!");
        return new BaseResponse<>(status, studentResponses);
    }
    public BaseResponse<Status, List<StudentResponse>> decGpa(int page, int limit) {
        int totalPage = (int)Math.ceil((double)studentRepository.count() / limit);
        List<StudentEntity> studentEntities = studentRepository.findAllByOrderByGpaDesc();
        List<StudentResponse> studentResponses = new ArrayList<>();
        for(StudentEntity studentEntity: studentEntities) {
            studentResponses.add(toDto.studentChange(studentEntity, totalPage, page));
        }
        studentResponses = studentPage(studentResponses, page, limit);
        Status status = new Status("000", "decrease students successful!");
        return new BaseResponse<>(status, studentResponses);
    }
    public BaseResponse<Status, List<Integer>> chartGpa() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 4; ++i) {
            list.add(0);
        }
        for(StudentEntity studentEntity: studentEntities) {
            double gpa = studentEntity.getGpa();
            if(gpa <= 1.0) {
                list.set(0, list.get(0) + 1);
            } else if(gpa <= 2.0) {
                list.set(1, list.get(1) + 1);
            } else if(gpa <= 3.0){
                list.set(2, list.get(2) + 1);
            } else {
                list.set(3, list.get(3) + 1);
            }
        }
        Status status = new Status("000", "show chart gpa successful!");
        return new BaseResponse<>(status, list);
    }
}





















