package com.thymeleaf.employeemanagementexample.service;

import com.thymeleaf.employeemanagementexample.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();
    void save(Employee employee);
    Optional<Employee> getById(Long id);
    void deleteById(Long id);
}
