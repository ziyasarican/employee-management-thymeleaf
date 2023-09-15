package com.thymeleaf.employeemanagementexample.errorHandler;

public class EmployeeNotFoundException extends  RuntimeException{

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
