package com.employee.employeeservice.database;

import com.employee.employeeservice.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeJdbcRepositoryTest {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;
    private EmployeeJdbcRepository employeesRepo;
    private Employee employee;
    @Before
    public void setup(){
        jdbcTemplate = mock(JdbcTemplate.class);
        namedTemplate = mock(NamedParameterJdbcTemplate.class);

        employeesRepo = new EmployeeJdbcRepository();

        ReflectionTestUtils.setField(employeesRepo,"jdbcTemplate",jdbcTemplate);
        ReflectionTestUtils.setField(employeesRepo,"template",namedTemplate);

        employee = new Employee("", "","123", "", "", "", "");
        when(jdbcTemplate.queryForObject(anyString(), anyObject(), (BeanPropertyRowMapper<Employee>)anyObject())).thenReturn(employee);
        when(namedTemplate.query(anyString(), (MapSqlParameterSource)anyObject(), (BeanPropertyRowMapper<Employee>)anyObject())).thenReturn(new ArrayList<Employee>(Arrays.asList(employee)));

    }

    @Test
    public void testGetEmployeeById(){
        Employee employee = employeesRepo.getById("123");
        Assert.assertEquals("123",employee.getEmployeeID());
    }

    @Test
    public void testGetManyEmployeeById(){
        List<String> manyIds = new ArrayList<String>(Arrays.asList("1", "2", "3"));
        List<Employee> employees = employeesRepo.getAllById(manyIds);
        when(jdbcTemplate.queryForObject(anyString(), anyObject(), (BeanPropertyRowMapper<Employee>)anyObject())).thenReturn(employee);
        Assert.assertEquals("123",employees.get(0).getEmployeeID());
    }
}
