package learn.spring.springbatch.batch2csv.processor;

import learn.spring.springbatch.batch2csv.model.Employee;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.ExecutionContext;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
    private final Set<Long> ids = new HashSet<>();
    private StepExecution stepExecution;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

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

        ExecutionContext executionContext = stepExecution.getExecutionContext();
        List<String> logs = (List<String>) executionContext.get("chunkLogs");
        if (logs == null) {
            logs = new ArrayList<>();
        }
        logs.add(String.format(
                "ID=%d | %s | %.2f",
                employee.getId(),
                employee.getName(),
                employee.getSalary())
        );
        executionContext.put("logs", logs);

        return employee;
    }
}
