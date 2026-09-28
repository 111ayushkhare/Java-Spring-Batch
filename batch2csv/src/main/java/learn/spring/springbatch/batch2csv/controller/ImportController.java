package learn.spring.springbatch.batch2csv.controller;

import learn.spring.springbatch.batch2csv.service.BatchService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/batch")
public class ImportController {
    private final BatchService batchService;

    public ImportController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping(value = "/import", consumes = "multipart/form-data")
    public String importCsv(@RequestParam("file")MultipartFile file) throws Exception {
        BatchStatus status = batchService.runBatch(file);
        return "Batch status: " + status;
    }
}
