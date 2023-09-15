package com.thymeleaf.employeemanagementexample.errorHandler;

public class DatabaseOperationException extends RuntimeException{
    public DatabaseOperationException(String message) {
        super(message);
    }

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
