package com.employee.employeeservice.controller;


import com.employee.employeeservice.database.EmployeeJdbcRepository;
import com.employee.employeeservice.exception.ResourceNotFoundException;
import com.employee.employeeservice.model.Employee;
import com.employee.employeeservice.model.Mentor;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {
    private String firstName;
    private String lastName;
    private String employeeID;
    private String employeeIDNotFound;
    private String office;
    private String title;
    private String email;
    private String imageURL;


    private Employee testEmployee;


    EmployeeJdbcRepository mockRepository;
    EmployeeController employeeController;

    @Before
    public void setup(){

        mockRepository = mock(EmployeeJdbcRepository.class);

        firstName = "Liz";
        lastName = "Johnson";
        employeeID = "123";
        employeeIDNotFound = "83";
        office = "Chicago";
        title = "Technical Analyst";
        email = "ej@solstice.com";
        imageURL = "www.image.com";

        testEmployee = new Employee(firstName, lastName, employeeID, office, title, email, imageURL);

        employeeController = new EmployeeController();
        ReflectionTestUtils.setField(employeeController, "repository", mockRepository);
    }
    @Test
    public void testGetEmployeeById() {
        when(mockRepository.getById("123")).thenReturn(testEmployee);

        Employee employee = employeeController.getEmployeeById(employeeID);

        Assert.assertEquals(firstName, employee.getFirstName());

    }

    @Test(expected=ResourceNotFoundException.class)
    public void testGetEmployeeById_EmployeeNotFound() {
        Employee employee = employeeController.getEmployeeById(employeeIDNotFound);

    }



}
