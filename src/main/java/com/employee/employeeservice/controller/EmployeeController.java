package com.employee.employeeservice.controller;

import com.employee.employeeservice.database.EmployeeJdbcRepository;
import com.employee.employeeservice.exception.ResourceNotFoundException;
import com.employee.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
