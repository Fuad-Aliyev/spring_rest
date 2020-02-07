package com.rest_jpa.rest_crud.controller;

import com.rest_jpa.rest_crud.model.Employee;
import com.rest_jpa.rest_crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/employee-management", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EmployeeRestController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id).get();
    }

    @PutMapping("/employees/{id}")
    Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id).map(
                employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setEmail(newEmployee.getEmail());
                    return employeeRepository.save(employee);
                }
        ).orElseGet(() -> {
           newEmployee.setId(id);
           return employeeRepository.save(newEmployee);
        });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
