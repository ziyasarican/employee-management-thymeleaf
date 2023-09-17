package com.thymeleaf.employeemanagementexample.service;

import com.thymeleaf.employeemanagementexample.controller.EmployeeController;
import com.thymeleaf.employeemanagementexample.entity.Employee;
import com.thymeleaf.employeemanagementexample.errorHandler.DatabaseOperationException;
import com.thymeleaf.employeemanagementexample.errorHandler.EmployeeNotFoundException;
import com.thymeleaf.employeemanagementexample.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override // JPA properties provide automatically getAll elements wtih findAll() method
    public List<Employee> getAll() {
        logger.info("Getting all employees.");
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        if (Objects.isNull(employee)) {
            throw new IllegalArgumentException("Employee object cannot be null.");
        }
        logger.info("Saving employee: " + employee);
        try {
            employeeRepository.save(employee);
            logger.info("Employee saved succesfully.");
        } catch (Exception e) {
            throw new DatabaseOperationException("An error occurred while saving employee data.", e);
        }
    }
    @Override
    public Employee getById(Long id) {
        logger.info("Getting employee by ID: " + id);
        try{
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            return optionalEmployee.orElseThrow(() ->
                    new EmployeeNotFoundException("Employee not found with the ID: " + id)
            );
        }catch (Exception e){
            throw new DatabaseOperationException("An error occurred while fetching employee data by ID.", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting employee by ID: " + id);
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
            } else {
                throw new EmployeeNotFoundException("Employee not found with the ID: " + id);
            }
        } catch (Exception e) {
            throw new DatabaseOperationException("An error occurred while deleting employee data by ID.", e);
        }
    }
}
