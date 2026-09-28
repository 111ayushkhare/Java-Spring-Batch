package learn.spring.springbatch.batch.one.config;

import learn.spring.springbatch.batch.one.model.Employee;
import learn.spring.springbatch.batch.one.reader.EmployeeRowMapper;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.JdbcCursorItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    JdbcCursorItemReader<Employee> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Employee>()
                .name("employeeReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM employee ORDER BY ID")
                .rowMapper(new EmployeeRowMapper())
                .build();
    }

    @Bean
    JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>()
                .dataSource(dataSource)
                .sql("""
                       INSERT INTO processed_employee 
                            (id, name, department, salary) 
                       VALUES (:id, :name, :department, :salary)
                """)
                .beanMapped()
                .build();
    }

    @Bean
    Job salaryJob(JobRepository repo, Step salaryStep) {
        return new JobBuilder("salaryJob", repo)
                .start(salaryStep)
                .build();
    }

    @Bean
    Step salaryStep(
            JobRepository repo,
            PlatformTransactionManager transactionManager,
            ItemReader<Employee> reader,
            ItemProcessor<Employee, Employee> processor,
            ItemWriter<Employee> writer
    ) {
        return new StepBuilder("salaryStep", repo)
                .<Employee, Employee>chunk(3)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .transactionManager(transactionManager)
                .build();
    }
}
