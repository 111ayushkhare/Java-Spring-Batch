package learn.spring.springbatch.batch2csv.controller;

import learn.spring.springbatch.batch2csv.dto.ApiResponse;
import learn.spring.springbatch.batch2csv.service.BatchService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<String>> importCsv(@RequestParam("file") MultipartFile file) {
        try {
            BatchStatus status = batchService.runBatch(file);
            if (status == BatchStatus.COMPLETED) {
                return ResponseEntity.ok(ApiResponse.success("Batch job completed successfully", status.toString()));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ApiResponse.error("Batch job failed or was stopped", status.toString()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred during batch processing", e.getMessage()));
        }
    }
}
