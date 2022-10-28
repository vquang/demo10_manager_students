package com.example.demo11.service;

import com.example.demo11.converter.ToDto;
import com.example.demo11.converter.ToEntity;
import com.example.demo11.dto.BaseResponse;
import com.example.demo11.dto.Status;
import com.example.demo11.dto.request.LecturerRequest;
import com.example.demo11.dto.response.LecturerResponse;
import com.example.demo11.entity.LecturerEntity;
import com.example.demo11.repository.ILecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LecturerService {
    @Autowired
    private ILecturerRepository lecturerRepository;
    @Autowired
    private ToEntity toEntity;
    @Autowired
    private ToDto toDto;
    public List<LecturerResponse> lecturerPage(List<LecturerResponse> lecturerResponses, int page, int limit) {
        List<LecturerResponse> lecturerResponseList = new ArrayList<>();
        int start = limit * (page - 1);
        int end = start + limit - 1;
        for(int i = start; i <= end && i < lecturerResponses.size(); ++i) {
            lecturerResponseList.add(lecturerResponses.get(i));
        }
        return lecturerResponseList;
    }
    public BaseResponse<Status, List<LecturerResponse>> showLecturers(int page, int limit) {
        int totalPages = (int)Math.ceil((double)lecturerRepository.count() / limit);
        List<LecturerEntity> lecturerEntities = lecturerRepository.findAll();
        List<LecturerResponse> lecturerResponses = new ArrayList<>();
        for(LecturerEntity lecturerEntity: lecturerEntities) {
            lecturerResponses.add(toDto.lecturerChange(lecturerEntity, totalPages, page));
        }
        lecturerResponses = lecturerPage(lecturerResponses, page, limit);
        Status status = new Status("000", "show lecturers successful!");
        return new BaseResponse<>(status, lecturerResponses);
    }
    public BaseResponse<Status, LecturerResponse> addLecturer(LecturerRequest lecturerRequest, int page, int limit) {
        int totalPage = (int)Math.ceil((double)lecturerRepository.count() / limit);
        LecturerEntity lecturerEntity = toEntity.lecturerChange(lecturerRequest);
        lecturerRepository.save(lecturerEntity);
        LecturerResponse lecturerResponse = toDto.lecturerChange(lecturerEntity, totalPage, page);
        Status status = new Status("000", "add lecturer successful!");
        return new BaseResponse<>(status, lecturerResponse);
    }
    public BaseResponse<Status, LecturerResponse> updateLecturer(Long id, LecturerRequest lecturerRequest, int page, int limit) {
        int totalPage = (int)Math.ceil((double)lecturerRepository.count() / limit);
        lecturerRequest.setId(id);
        LecturerEntity lecturerEntity = lecturerRepository.findById(id).orElse(null);
        lecturerEntity = toEntity.lecturerChange(lecturerRequest);
        lecturerRepository.save(lecturerEntity);
        LecturerResponse lecturerResponse = toDto.lecturerChange(lecturerEntity, totalPage, page);
        Status status = new Status("000", "update lecturer successful!");
        return new BaseResponse<>(status, lecturerResponse);
    }
    public BaseResponse<Status, LecturerResponse> deleteLecturer(Long id, int page, int limit) {
        int totalPage = (int)Math.ceil((double)lecturerRepository.count() / limit);
        LecturerEntity lecturerEntity = lecturerRepository.findById(id).orElse(null);
        LecturerResponse lecturerResponse = toDto.lecturerChange(lecturerEntity, totalPage, page);
        lecturerRepository.delete(lecturerEntity);
        Status status = new Status("000", "delete lecturer successful!");
        return new BaseResponse<>(status, lecturerResponse);
    }
    public BaseResponse<Status, List<LecturerResponse>> searchLecturers(String search, int page, int limit) {
        int totalPage = (int)Math.ceil((double)lecturerRepository.count() / limit);
        search = "%" + search + "%";
        List<LecturerEntity> lecturerEntities = lecturerRepository.findByNameLike(search);
        totalPage = (int)Math.ceil((double)lecturerEntities.size() / limit);
        List<LecturerResponse> lecturerResponses = new ArrayList<>();
        for(LecturerEntity lecturerEntity: lecturerEntities) {
            lecturerResponses.add(toDto.lecturerChange(lecturerEntity, totalPage, page));
        }
        lecturerResponses = lecturerPage(lecturerResponses, page, limit);
        Status status = new Status("000", "search lecturers successful!");
        return new BaseResponse<>(status, lecturerResponses);
    }
    public BaseResponse<Status, List<LecturerResponse>> showLevel(String level, int page, int limit) {
        int totalPage = (int)Math.ceil((double)lecturerRepository.count() / limit);
        List<LecturerEntity> lecturerEntities = lecturerRepository.findByLevel(level);
        totalPage = (int)Math.ceil((double)lecturerEntities.size() / limit);
        List<LecturerResponse> lecturerResponses = new ArrayList<>();
        for(LecturerEntity lecturerEntity: lecturerEntities) {
            lecturerResponses.add(toDto.lecturerChange(lecturerEntity, totalPage, page));
        }
        lecturerResponses = lecturerPage(lecturerResponses, page, limit);
        Status status = new Status("000", "show level successful!");
        return new BaseResponse<>(status, lecturerResponses);
    }
    public BaseResponse<Status, LecturerResponse> updateLevel(Long id, String level, int page, int limit) {
        int totalPage = (int)Math.ceil((double)lecturerRepository.count() / limit);
        LecturerEntity lecturerEntity = lecturerRepository.findById(id).orElse(null);
        lecturerEntity.setLevel(level);
        lecturerRepository.save(lecturerEntity);
        LecturerResponse lecturerResponse = toDto.lecturerChange(lecturerEntity, totalPage, page);
        Status status = new Status("000", "update level successful!");
        return new BaseResponse<>(status, lecturerResponse);
    }
    public BaseResponse<Status, List<Integer>> chartLevel() {
        List<LecturerEntity> lecturerEntities = lecturerRepository.findAll();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 3; ++i) {
            list.add(0);
        }
        for(LecturerEntity lecturerEntity: lecturerEntities) {
            String level = lecturerEntity.getLevel();
            if(level.equals("Giáo Sư")) {
                list.set(0, list.get(0) + 1);
            } else if(level.equals("Tiến Sĩ")) {
                list.set(1, list.get(1) + 1);
            } else {
                list.set(2, list.get(2) + 1);
            }
        }
        Status status = new Status("000", "show chart level successful!");
        return new BaseResponse<>(status, list);
    }
}





















