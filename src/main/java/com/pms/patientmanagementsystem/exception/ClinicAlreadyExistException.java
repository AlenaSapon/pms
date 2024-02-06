package com.pms.patientmanagementsystem.exception;

public class ClinicAlreadyExistException extends RuntimeException {
    public ClinicAlreadyExistException(String message) {
        super(message);
    }
}
