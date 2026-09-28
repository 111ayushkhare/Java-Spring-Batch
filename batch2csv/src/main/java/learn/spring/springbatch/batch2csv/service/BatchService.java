package learn.spring.springbatch.batch2csv.service;

import org.springframework.batch.core.BatchStatus;
import org.springframework.web.multipart.MultipartFile;

public interface BatchService {
    BatchStatus runBatch(MultipartFile file) throws Exception;
}
