package learn.spring.springbatch.batch2csv.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ChunkListener implements org.springframework.batch.core.listener.ChunkListener {
    @Override
    public void beforeChunk(ChunkContext context) {
        // No action needed
    }

    @Override
    public void afterChunk(ChunkContext context) {

        StepExecution stepExecution = context.getStepContext().getStepExecution();

        ExecutionContext executionContext =
                stepExecution.getExecutionContext();

        List<String> logs = (List<String>) executionContext.get("chunkLogs");

        if (logs == null || logs.isEmpty()) {
            return;
        }

        log.info("");
        log.info("========== CHUNK START ==========");
        log.info("Chunk Size : {}", logs.size());

        logs.forEach(log::info);

        log.info("=========== CHUNK END ===========");
        log.info("");

        executionContext.remove("chunkLogs");
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        context.getStepContext()
                .getStepExecution()
                .getExecutionContext()
                .remove("chunkLogs");
    }
}
