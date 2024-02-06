package com.pms.patientmanagementsystem.exception;

public class DocumentAlreadyExistException extends RuntimeException {
    public DocumentAlreadyExistException(String message) {
        super(message);
    }
}
