package learn.spring.springbatch.batch2csv.config;

import learn.spring.springbatch.batch2csv.listener.ChunkListener;
import learn.spring.springbatch.batch2csv.listener.JobListener;
import learn.spring.springbatch.batch2csv.listener.StepListener;
import learn.spring.springbatch.batch2csv.model.Employee;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
    @Bean
    Job importEmployeeJob(
            JobRepository jobRepository,
            JobListener jobListener,
            Step step
    ) {
        return new JobBuilder("importEmployees", jobRepository)
                .listener(jobListener)
                .start(step)
                .build();
    }

    @Bean
    Step step(
            JobRepository jobRepository,
            StepListener stepListener,
            PlatformTransactionManager transactionManager,
            FlatFileItemReader<Employee> reader,
            ItemProcessor<Employee, Employee> processor,
            JdbcBatchItemWriter<Employee> writer,
            ChunkListener chunkListener
    ) {
        return new StepBuilder("step", jobRepository)
                .<Employee, Employee>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .transactionManager(transactionManager)
                .listener(stepListener)
                .listener(chunkListener)
                .build();
    }
}
