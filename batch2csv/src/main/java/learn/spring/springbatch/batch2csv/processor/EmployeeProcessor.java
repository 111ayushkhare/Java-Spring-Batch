package learn.spring.springbatch.batch2csv.processor;

import learn.spring.springbatch.batch2csv.model.Employee;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
    private final Set<Long> ids = new HashSet<>();

    @Override
    public @Nullable Employee process(Employee employee) throws Exception {
        employee.setName(employee.getName().trim());
        employee.setDepartment(employee.getDepartment().trim().toUpperCase());

        // Validation
        if (employee.getSalary() <= 0) {
            return null;
        }

        // Duplicate within csv
        if (!ids.add(employee.getId())) {
            return null;
        }

        return employee;
    }
}
