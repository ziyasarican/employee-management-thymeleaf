package com.thymeleaf.employeemanagementexample.repository;

import com.thymeleaf.employeemanagementexample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
