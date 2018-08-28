package com.employee.employeeservice.controller;

import com.employee.employeeservice.database.EmployeeJdbcRepository;
import com.employee.employeeservice.exception.ResourceNotFoundException;
import com.employee.employeeservice.model.Employee;
import com.employee.employeeservice.model.EmployeeList;
import com.employee.employeeservice.model.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeList employees;

    @Autowired
    private EmployeeJdbcRepository repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployeeById(@PathVariable("id") String id) {
       // return new Employee("Liz", "Johnson", "123", "", "" ,"" ,"");
        // employees.getEmployeeMap().get(id);
        return repository.getById(id);
    }

    @RequestMapping(value = "/mentor/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Employee> getEmployeesByMentorID(@RequestParam String id) {
        if(employees.getEmployeeMap().get(id) != null) {
            Employee employee = employees.getEmployeeMap().get(id);
            if (employee instanceof Mentor) {
                Mentor mentor = (Mentor) employee;
                return mentor.getMenteesList().values();
            }
        }
        throw new ResourceNotFoundException();
    }

    public Employee deleteEmployeeByID(String employeeId) {
        if(employees.getEmployeeMap().get(employeeId) != null){
            Employee deletedEmployee = employees.getEmployeeMap().get(employeeId);
            employees.getEmployeeMap().remove(employeeId);
            return deletedEmployee;
        }
        throw new ResourceNotFoundException();
    }
}
