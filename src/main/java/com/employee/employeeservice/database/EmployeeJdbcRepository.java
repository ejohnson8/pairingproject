package com.employee.employeeservice.database;

import com.employee.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeJdbcRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Employee getById(String id) {
        return jdbcTemplate.queryForObject("select * from employee where employeeID=?", new Object[] {id}, new BeanPropertyRowMapper< Employee >(Employee.class));
    }

    public List<Employee> getAllById(List<String> ids) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", ids);

        return template.query("SELECT * FROM employee WHERE employeeID IN (:ids)", parameters, new BeanPropertyRowMapper<Employee>(Employee.class));

    }
}
