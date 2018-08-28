package com.employee.employeeservice.database;

import com.employee.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeJdbcRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Employee getById(String id) {
        return jdbcTemplate.queryForObject("select * from employee where employeeID=?", new Object[] {
                id
        }, new BeanPropertyRowMapper< Employee >(Employee.class));
    }
}
