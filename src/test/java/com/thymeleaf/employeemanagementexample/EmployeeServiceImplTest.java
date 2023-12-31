package com.thymeleaf.employeemanagementexample;


import com.thymeleaf.employeemanagementexample.entity.Employee;
import com.thymeleaf.employeemanagementexample.errorHandler.DatabaseOperationException;
import com.thymeleaf.employeemanagementexample.errorHandler.EmployeeNotFoundException;
import com.thymeleaf.employeemanagementexample.repository.EmployeeRepository;
import com.thymeleaf.employeemanagementexample.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll(){

        List<Employee> mockEmployeeList = List.of(
                new Employee(1L,"ziya","ziya","1","MIS"),
                new Employee(2L,"ziya2","ziya2@gmail.com","2","MIS2")
                );

        when(employeeRepository.findAll()).thenReturn(mockEmployeeList);

        List<Employee> employees = employeeService.getAll();

        assertEquals(2, employees.size());
        assertEquals("ziya",employees.get(0).getName());

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testSave(){
        // if
        Employee nullEmployee = null;

        doThrow(new IllegalArgumentException("Employee cannot be null")).when(employeeRepository).save(nullEmployee);
        assertThrows(IllegalArgumentException.class, () -> employeeService.save(nullEmployee));

        //try
        Mockito.reset(employeeRepository);
        Employee employee = new Employee(1L, "ziya", "ziya@gmail.com", "1", "MIS");

        employeeService.save(employee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        verify(employeeRepository, times(1)).save(any(Employee.class));

        //catch
        Mockito.reset(employeeRepository);
        Employee catchEmployee = new Employee();

        when(employeeRepository.save(catchEmployee)).thenThrow(new RuntimeException("An error occurred while saving employee data."));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> employeeService.save(catchEmployee));
        assertEquals("An error occurred while saving employee data.", exception.getMessage());
        verify(employeeRepository).save(catchEmployee);
    }

    @Test
    void testGetById(){
        Employee testEmployee = new Employee(1L, "ziya", "srcn", "01", "MIS");

        when(employeeRepository.findById(testEmployee.getId())).thenReturn(Optional.of(testEmployee));
        assertEquals(testEmployee.getId(), employeeService.getById(testEmployee.getId()).get().getId());

        Mockito.reset(employeeRepository);
        Employee test2Employee = new Employee(1L, "ziya", "srcn", "01", "MIS");
        when(employeeRepository.findById(test2Employee.getId())).thenThrow(new RuntimeException("An error occurred while fetching employee data by ID."));
        assertThrows(DatabaseOperationException.class, () -> employeeService.getById(test2Employee.getId()));
    }

    @Test
    void testDeleteById(){
        Long employeeId = 1L;

        when(employeeRepository.existsById(employeeId)).thenReturn(true);
        employeeService.deleteById(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);

        Mockito.reset(employeeRepository);
        when(employeeRepository.existsById(employeeId)).thenReturn(false);
        assertThrows(DatabaseOperationException.class, () -> employeeService.deleteById(employeeId));


    }
}
