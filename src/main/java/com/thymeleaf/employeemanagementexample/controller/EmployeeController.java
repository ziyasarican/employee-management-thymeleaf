package com.thymeleaf.employeemanagementexample.controller;

import com.thymeleaf.employeemanagementexample.entity.Employee;
import com.thymeleaf.employeemanagementexample.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("employeeList", employeeService.getAll());
        logger.info("Home page accessed.");
        return "index";
    }

    @GetMapping("/add")
    public String saveEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        logger.info("Add employee page accessed.");
        return "add";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        logger.info("Employee saved: " + employee.getName());
        return "redirect:/";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable(value = "id") long id, Model model){
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        logger.info("Update employee page accessed for employee with ID: " + id);
        return "update";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(value = "id") long id){
        employeeService.deleteById(id);
        logger.info("Employee deleted with ID: " + id);
        return "redirect:/";
    }
}
