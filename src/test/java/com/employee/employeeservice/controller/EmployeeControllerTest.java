package com.employee.employeeservice.controller;


import com.employee.employeeservice.model.Employee;
import com.employee.employeeservice.model.EmployeeList;
import com.employee.employeeservice.model.Mentor;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
    private Map<String, Employee> employeeList;
    private String mentorID;

    EmployeeController employeeController;

    @Before
    public void setup(){
        EmployeeList mockEmployeeList;
        mockEmployeeList = mock(EmployeeList.class);

        firstName = "Liz";
        lastName = "Johnson";
        employeeID = "123";
        employeeIDNotFound = "83";
        office = "Chicago";
        title = "Technical Analyst";
        email = "ej@solstice.com";
        imageURL = "www.image.com";
        mentorID = "456";
        Employee testEmployee = new Employee(firstName, lastName, employeeID, office, title, email, imageURL);


        employeeList = new HashMap<String, Employee>();
        employeeList.put(employeeID, testEmployee);
        Map<String,Employee> employeesLowestLevel = employeeList;
        Employee testMentor = new Mentor(firstName, lastName, mentorID, office, title, email, imageURL, employeesLowestLevel);
        employeeList.put(mentorID, testMentor);


        when(mockEmployeeList.getEmployeeMap()).thenReturn(employeeList);

        employeeController = new EmployeeController();
        ReflectionTestUtils.setField(employeeController, "employees", mockEmployeeList);
    }
    @Test
    public void testGetEmployeeById() {
        Employee employee = employeeController.getEmployeeById(employeeID);

        Assert.assertEquals(firstName, employee.getFirstName());

    }

    @Test
    public void testGetEmployeeById_EmployeeNotFound() {
        Employee employee = employeeController.getEmployeeById(employeeIDNotFound);

        Assert.assertNull(employee);

    }

    @Test
    public void testDeleteEmployeeByID(){

        int numRecords = employeeList.size();
        Employee deletedEmployee = employeeController.deleteEmployeeByID(employeeID);
        Assert.assertEquals(employeeID,deletedEmployee.getEmployeeID());
        Assert.assertEquals(numRecords - 1, employeeList.size());

    }
    @Test
    public void testDeleteEmployeeByID_NotFound(){

        Employee deletedEmployee = employeeController.deleteEmployeeByID(employeeIDNotFound);
        Assert.assertNull(deletedEmployee);

    }

    @Test
    public void testGetEmployeesByMentorID(){
        Collection<Employee> mentees = employeeController.getEmployeesByMentorID(mentorID);

    }

}
