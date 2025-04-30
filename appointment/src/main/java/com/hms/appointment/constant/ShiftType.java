package com.hms.appointment.constant;

public enum ShiftType {
    MORNING("CA SÁNG"),
    AFTERNOON("CA CHIỀU"),
    NIGHT("CA TỐI");

    private final String shiftName;

    ShiftType(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getShiftName() {
        return shiftName;
    }
}
