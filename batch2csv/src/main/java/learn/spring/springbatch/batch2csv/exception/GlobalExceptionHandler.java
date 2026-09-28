package learn.spring.springbatch.batch2csv.exception;

import learn.spring.springbatch.batch2csv.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.JobExecutionException;
import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex) {
        log.error("An unexpected error occurred: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("An unexpected error occurred", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Invalid argument provided: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Invalid request parameters", ex.getMessage()));
    }

    @ExceptionHandler(JobExecutionException.class)
    public ResponseEntity<ApiResponse<String>> handleJobExecutionException(JobExecutionException ex) {
        log.error("Spring Batch job execution failed: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Batch Job failed to execute properly", ex.getMessage()));
    }

    @ExceptionHandler(JobInstanceAlreadyCompleteException.class)
    public ResponseEntity<ApiResponse<String>> handleJobInstanceAlreadyCompleteException(JobInstanceAlreadyCompleteException ex) {
        log.error("Batch job instance already complete: ", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error("This batch job has already been completed", ex.getMessage()));
    }

    @ExceptionHandler(InvalidJobParametersException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidJobParametersException(InvalidJobParametersException ex) {
        log.error("Invalid job parameters provided: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Invalid batch job parameters", ex.getMessage()));
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiResponse<String>> handleMultipartException(MultipartException ex) {
        log.error("File upload error: ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Failed to process the uploaded file", ex.getMessage()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<String>> handleDataAccessException(DataAccessException ex) {
        log.error("Database error occurred: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("A database error occurred during processing", ex.getMostSpecificCause().getMessage()));
    }
}
