package com.pms.patientmanagementsystem.exception;

public class ClinicNotFoundException extends RuntimeException {
    public ClinicNotFoundException(String message) {
        super(message);
    }
}
