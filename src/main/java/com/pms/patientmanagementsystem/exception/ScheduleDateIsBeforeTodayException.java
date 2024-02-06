package com.pms.patientmanagementsystem.exception;

public class ScheduleDateIsBeforeTodayException extends RuntimeException {
    public ScheduleDateIsBeforeTodayException(String message) {
        super(message);
    }
}
