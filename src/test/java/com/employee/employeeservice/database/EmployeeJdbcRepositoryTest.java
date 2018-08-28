package com.employee.employeeservice.database;

import com.employee.employeeservice.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeJdbcRepositoryTest {
    private JdbcTemplate jdbcTemplate;
    private EmployeeJdbcRepository employeesRepo;
    private Employee employee;
    @Before
    public void setup(){
        jdbcTemplate = mock(JdbcTemplate.class);
        employeesRepo = new EmployeeJdbcRepository();
        ReflectionTestUtils.setField(employeesRepo,"jdbcTemplate",jdbcTemplate);
        employee = new Employee("", "","123", "", "", "", "");
        when(jdbcTemplate.queryForObject(anyString(), anyObject(), (BeanPropertyRowMapper<Employee>)anyObject())).thenReturn(employee);

    }

    @Test
    public void testGetEmployeeById(){
        Employee employee = employeesRepo.getById("123");
        Assert.assertEquals("123",employee.getEmployeeID());
    }
}
