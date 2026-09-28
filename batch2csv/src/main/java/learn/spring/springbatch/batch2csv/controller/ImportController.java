package learn.spring.springbatch.batch2csv.controller;

import learn.spring.springbatch.batch2csv.service.BatchService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class ImportController {
    private final BatchService batchService;

    public ImportController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/import")
    public String runBatch() throws Exception {
        BatchStatus status = batchService.runBatch();
        return "Batch status: " + status;
    }
}
