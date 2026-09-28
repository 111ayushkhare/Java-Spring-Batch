package learn.spring.springbatch.batch2csv.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {

        log.info("======================================");
        log.info("JOB EXECUTION ID : {}", jobExecution.getId());
        log.info("JOB NAME         : {}",
                jobExecution.getJobInstance().getJobName());
        log.info("STATUS           : {}", jobExecution.getStatus());
        log.info("======================================");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        log.info("JOB EXECUTION ID : {}", jobExecution.getId());
        log.info("FINAL STATUS     : {}", jobExecution.getStatus());
        log.info("START TIME       : {}", jobExecution.getStartTime());
        log.info("END TIME         : {}", jobExecution.getEndTime());
    }
}