package com.pms.patientmanagementsystem.exception;

public class ScheduleAlreadyExistException extends RuntimeException {
    public ScheduleAlreadyExistException(String message) {
        super(message);
    }
}
