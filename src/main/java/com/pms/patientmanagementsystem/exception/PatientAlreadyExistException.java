package com.pms.patientmanagementsystem.exception;

public class PatientAlreadyExistException extends RuntimeException {
    public PatientAlreadyExistException(String message) {
        super(message);
    }
}
