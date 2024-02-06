package com.pms.patientmanagementsystem.exception;

public class StatusHistoryAlreadyExistException extends RuntimeException {
    public StatusHistoryAlreadyExistException(String message) {
        super(message);
    }
}
