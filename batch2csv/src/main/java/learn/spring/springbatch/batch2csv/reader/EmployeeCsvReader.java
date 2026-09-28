package learn.spring.springbatch.batch2csv.reader;

import learn.spring.springbatch.batch2csv.model.Employee;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCsvReader {
    @Bean
    public FlatFileItemReader<Employee> reader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeCsvReader")
                .resource(new ClassPathResource("employee.csv"))
                .linesToSkip(1)
                .delimited()
                .names("id", "name", "department", "salary")
                .targetType(Employee.class)
                .build();
    }
}
