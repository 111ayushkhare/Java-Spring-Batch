package learn.spring.springbatch.batch.one.reader;

import learn.spring.springbatch.batch.one.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();

        employee.setId(rs.getLong("id"));
        employee.setName(rs.getString("name"));
        employee.setDepartment(rs.getString("department"));
        employee.setSalary(rs.getDouble("salary"));

        return employee;
    }
}
