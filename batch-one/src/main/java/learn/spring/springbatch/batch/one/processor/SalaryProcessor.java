package learn.spring.springbatch.batch.one.processor;

import learn.spring.springbatch.batch.one.model.Employee;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SalaryProcessor implements ItemProcessor<Employee, Employee> {

    @Override
    public @Nullable Employee process(Employee item) throws Exception {
        if (item.getSalary() < 50000) {
            return null;
        }

        item.setSalary(item.getSalary() * 1.2);

        return item;
    }
}
