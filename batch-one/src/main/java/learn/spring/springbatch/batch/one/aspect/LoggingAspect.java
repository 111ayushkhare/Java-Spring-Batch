package learn.spring.springbatch.batch.one.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    // Runs before SalaryProcessor.process()
    @Before("execution(* learn.spring.springbatch.batch.one.processor..*.*(..))")
    public void beforeExecution(JoinPoint joinPoint) {
        log.info("========== START ==========");
        log.info("Class  : {}", joinPoint.getTarget().getClass().getSimpleName());
        log.info("Method : {}", joinPoint.getSignature().getName());
    }

    // Runs only when method completes successfully
    @AfterReturning(
            pointcut = "execution(* learn.spring.springbatch.batch.one.processor..*.*(..))",
            returning = "result"
    )
    public void afterSuccess(JoinPoint joinPoint, Object result) {
        log.info("Method {} completed successfully",
                joinPoint.getSignature().getName());

        log.info("Returned Object : {}", result);
        log.info("========== END ==========\n");
    }

    // Runs only when exception occurs
    @AfterThrowing(
            pointcut = "execution(* learn.spring.springbatch.batch.one.processor..*.*(..))",
            throwing = "exception"
    )
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        log.error("Method {} failed : {}",
                joinPoint.getSignature().getName(),
                exception.getMessage());
    }
}
