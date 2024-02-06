package com.pms.patientmanagementsystem.exception;

public class PatientCaseNotFoundException extends RuntimeException {
    public PatientCaseNotFoundException(String message) {
        super(message);
    }
}
