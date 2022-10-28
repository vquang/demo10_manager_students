package com.example.demo11.repository;

import com.example.demo11.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findByNameLike(String search);
    List<StudentEntity> findAllByOrderByGpaAsc();
    List<StudentEntity> findAllByOrderByGpaDesc();
}
