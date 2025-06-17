package com.gen.api.enums;

public enum Shift {

    MORNING("Morning"),
    NIGHT("Night");

    private String shift;

    private Shift(String shift) {
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

}
