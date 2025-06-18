package com.gen.api.model.mapper;

import org.mapstruct.Mapper;

import com.gen.api.model.User;
import com.gen.api.model.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserEntity entity);

    UserEntity toEntity(User model);

}
