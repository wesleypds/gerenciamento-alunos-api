package com.gen.api.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gen.api.model.User;
import com.gen.api.model.entity.UserEntity;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserEntity entity);

    UserEntity toEntity(User model);

}
