package learn.spring.springbatch.batch2csv.service.impl;

import learn.spring.springbatch.batch2csv.service.BatchService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class BatchServiceImpl implements BatchService {
    private final JobOperator jobOperator;
    private final Job importJob;

    public BatchServiceImpl(JobOperator jobOperator, Job importJob) {
        this.jobOperator = jobOperator;
        this.importJob = importJob;
    }

    @Override
    public BatchStatus runBatch(MultipartFile file) throws Exception {
        Path tempFile = Files.createTempFile("employees-", ".csv");
        Files.copy(
                file.getInputStream(),
                tempFile,
                StandardCopyOption.REPLACE_EXISTING
        );

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", tempFile.toString())
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution = jobOperator.run(importJob, jobParameters);
        return jobExecution.getStatus();
    }
}
