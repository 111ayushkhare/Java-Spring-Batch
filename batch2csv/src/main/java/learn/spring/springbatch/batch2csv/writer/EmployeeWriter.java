package learn.spring.springbatch.batch2csv.writer;

import learn.spring.springbatch.batch2csv.model.Employee;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class EmployeeWriter {
    @Bean
    public JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>()
                .dataSource(dataSource)
                .sql("""
                        INSERT INTO employee
                        (id, name, department, salary)
                        VALUES (:id, :name, :department, :salary)
                        """)
                .beanMapped()
                .build();
    };
}
