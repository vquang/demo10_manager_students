package com.example.demo11.repository;

import com.example.demo11.entity.LecturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILecturerRepository extends JpaRepository<LecturerEntity, Long> {
    List<LecturerEntity> findByNameLike(String search);
    List<LecturerEntity> findByLevel(String level);
}
