package learn.spring.springbatch.batch2csv.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {

        log.info("--------------------------------------");
        log.info("STEP EXECUTION ID : {}", stepExecution.getId());
        log.info("STEP NAME         : {}", stepExecution.getStepName());
        log.info("--------------------------------------");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        log.info("Read Count   : {}", stepExecution.getReadCount());
        log.info("Write Count  : {}", stepExecution.getWriteCount());
        log.info("Skip Count   : {}", stepExecution.getSkipCount());
        log.info("Commit Count : {}", stepExecution.getCommitCount());

        return stepExecution.getExitStatus();
    }
}
