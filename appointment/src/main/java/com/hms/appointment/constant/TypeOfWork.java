package com.hms.appointment.constant;


public enum TypeOfWork {
    EXAMINATION("KHÁM BỆNH"),
    OPERATION("VẬN HÀNH"),
    TESTING("XÉT NGHIỆM"),
    SURGERY("PHẪU THUẬT");

    private String typeOfWork;

    TypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }
}
