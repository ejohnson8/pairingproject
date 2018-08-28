package com.employee.employeeservice.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeList {
    private Map<String, Employee> employeeMap;

    public Map<String, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public void setEmployeeMap(Map<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    public void addNewEmployee(Employee employee) {
        if(employeeMap == null) {
            employeeMap = new HashMap<String, Employee>();
        }

        employeeMap.put(employee.getEmployeeID(), employee);
    }
}
