package com.employee.employeeservice.controller;

import com.employee.employeeservice.database.EmployeeJdbcRepository;
import com.employee.employeeservice.exception.ResourceNotFoundException;
import com.employee.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeJdbcRepository repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployeeById(@PathVariable("id") String id) {
        Employee employee = repository.getById(id);
        if(employee != null) {
            return employee;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getAllEmployeesByIds(@PathVariable("ids") List<String> ids) {
        List<Employee> employees = repository.getAllById(ids);
        if(employees != null) {
            return employees;
        } else {
            throw new ResourceNotFoundException();
        }
    }

}
