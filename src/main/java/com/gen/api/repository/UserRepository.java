package com.gen.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gen.api.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.email =:email")
    UserEntity findByEmail(@Param("email") String email);

    @Query("SELECT u FROM UserEntity u WHERE u.user =:user AND u.password =:password")
    UserEntity getLogin(@Param("user") String user, @Param("password") String password);
}
