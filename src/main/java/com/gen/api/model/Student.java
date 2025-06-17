package com.gen.api.model;

import com.gen.api.enums.Course;
import com.gen.api.enums.Shift;
import com.gen.api.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Student {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String enrollment;

    private Course course;

    @NotNull
    private Status status;

    private Shift shift;

    public Student() {}

    public Student(Long id, String name, String enrollment, Course course, Status status, Shift shift) {
        this.id = id;
        this.name = name;
        this.enrollment = enrollment;
        this.course = course;
        this.status = status;
        this.shift = shift;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

}
