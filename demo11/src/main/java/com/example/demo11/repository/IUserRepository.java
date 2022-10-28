package com.example.demo11.repository;


import com.example.demo11.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
