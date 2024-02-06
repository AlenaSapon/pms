package com.pms.patientmanagementsystem.exception;

public class StatusAlreadyExistException extends RuntimeException {
    public StatusAlreadyExistException(String message) {
        super(message);
    }
}
