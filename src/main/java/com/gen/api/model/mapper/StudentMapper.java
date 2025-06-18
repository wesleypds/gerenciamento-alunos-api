package com.gen.api.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.gen.api.model.Student;
import com.gen.api.model.entity.StudentEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {

    Student toModel(StudentEntity entity);

    StudentEntity toEntity(Student model);

}
