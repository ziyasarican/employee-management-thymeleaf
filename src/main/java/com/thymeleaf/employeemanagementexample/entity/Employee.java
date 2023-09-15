package com.thymeleaf.employeemanagementexample.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employee")
    @SequenceGenerator(name = "Employee", sequenceName = "EMPLOYEE_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "AGE", length = 3)
    private String age;

    @Column(name = "DEPARTMENT", length = 11)
    private String department;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", designation='" + department + '\'' +
                '}';
    }
}
