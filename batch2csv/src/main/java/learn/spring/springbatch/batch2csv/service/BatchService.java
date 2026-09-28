package learn.spring.springbatch.batch2csv.service;

import org.springframework.batch.core.BatchStatus;

public interface BatchService {
    BatchStatus runBatch() throws Exception;
}
