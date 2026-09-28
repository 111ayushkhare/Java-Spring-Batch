package learn.spring.springbatch.batch2csv.reader;

import learn.spring.springbatch.batch2csv.model.Employee;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCsvReader {

    @Bean
    @StepScope
    public FlatFileItemReader<Employee> reader(@Value("#{jobParameters['filePath']}") String filePath) {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeCsvReader")
                .resource(new FileSystemResource(filePath))
                .linesToSkip(1)
                .delimited()
                .names("id", "name", "department", "salary")
                .targetType(Employee.class)
                .build();
    }
}
