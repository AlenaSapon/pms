package com.pms.patientmanagementsystem.exception;

public class InDepartmentAlreadyExistException extends RuntimeException {
    public InDepartmentAlreadyExistException(String message) {
        super(message);
    }
}
