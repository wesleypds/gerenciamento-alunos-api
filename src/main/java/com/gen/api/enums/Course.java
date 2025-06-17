package com.gen.api.enums;

public enum Course {

    ADMINISTRATION("Administration"),
    COMPUTER("Computer"),
    ACCOUNTING("Accounting"),
    NURSING("Nursing"),
    BIOMEDICAL_SCIENCE("Biomedical Science"),
    LAW("Law");

    private String course;

    private Course(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

}
