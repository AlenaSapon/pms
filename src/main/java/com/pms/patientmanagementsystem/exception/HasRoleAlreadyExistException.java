package com.pms.patientmanagementsystem.exception;

public class HasRoleAlreadyExistException extends RuntimeException {
    public HasRoleAlreadyExistException(String message) {
        super(message);
    }
}
