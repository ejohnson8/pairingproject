package com.employee.employeeservice.database;

import com.employee.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Employee getById(String id) {
        return jdbcTemplate.queryForObject("select * from employee where employeeID=?", new Object[] {
                id
        }, new BeanPropertyRowMapper< Employee >(Employee.class));
    }

    public List<Employee> getAllById(List<String> ids) {
        return jdbcTemplate.query("select * from employee where employeeID in ids", new BeanPropertyRowMapper<Employee>(Employee.class));
    }
}
