package com.gen.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gen.api.model.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("SELECT u FROM StudentEntity u WHERE u.status = 'ACTIVE'")
    List<StudentEntity> findByStatusActive();

    @Query("SELECT u FROM StudentEntity u WHERE u.status = 'INACTIVE'")
    List<StudentEntity> findByStatusInactive();

    List<StudentEntity> findByNameContainingIgnoreCase(String name);
}