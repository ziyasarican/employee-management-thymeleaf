package com.thymeleaf.employeemanagementexample.service;

import com.thymeleaf.employeemanagementexample.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    void save(Employee employee);
    Employee getById(Long id);
    void deleteById(Long id);
}
