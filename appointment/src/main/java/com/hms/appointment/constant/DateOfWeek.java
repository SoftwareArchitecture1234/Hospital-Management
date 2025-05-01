package com.hms.appointment.constant;

public enum DateOfWeek {
    MONDAY("THỨ 2"),
    TUESDAY("THỨ 3"),
    WEDNESDAY("THỨ 4"),
    THURSDAY("THỨ 5"),
    FRIDAY("THỨ 6"),
    SATURDAY("THỨ 7"),
    SUNDAY("CHỦ NHẬT");

    private final String day;

    DateOfWeek(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
