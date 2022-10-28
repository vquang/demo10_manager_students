package com.example.demo11.repository;

import com.example.demo11.entity.MajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMajorRepository extends JpaRepository<MajorEntity, Long> {
    MajorEntity findByName(String name);
}
