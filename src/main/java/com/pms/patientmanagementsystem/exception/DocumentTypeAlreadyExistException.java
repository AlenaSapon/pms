package com.pms.patientmanagementsystem.exception;

public class DocumentTypeAlreadyExistException extends RuntimeException {
    public DocumentTypeAlreadyExistException(String message) {
        super(message);
    }
}
