package learn.spring.springbatch.batch2csv.service.impl;

import jakarta.websocket.server.ServerEndpoint;
import learn.spring.springbatch.batch2csv.service.BatchService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements BatchService {
    private final JobOperator jobOperator;
    private final Job importJob;

    public BatchServiceImpl(JobOperator jobOperator, Job importJob) {
        this.jobOperator = jobOperator;
        this.importJob = importJob;
    }

    @Override
    public BatchStatus runBatch() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution = jobOperator.run(importJob, jobParameters);
        return jobExecution.getStatus();
    }
}
