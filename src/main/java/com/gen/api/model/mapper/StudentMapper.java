package com.gen.api.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gen.api.model.Student;
import com.gen.api.model.entity.StudentEntity;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toModel(StudentEntity entity);

    StudentEntity toEntity(Student model);

}
