package learn.spring.springbatch.batch2csv.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {
    @Before("within(@org.springframework.stereotype.Service *)")
    public void before(JoinPoint joinPoint) {

        log.info("========== SERVICE START ==========");
        log.info("Method : {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(
            pointcut = "within(@org.springframework.stereotype.Service *)",
            returning = "result")
    public void after(JoinPoint joinPoint, Object result) {

        log.info("Result : {}", result);
        log.info("=========== SERVICE END ===========");
    }

    @AfterThrowing(
            pointcut = "within(@org.springframework.stereotype.Service *)",
            throwing = "exception")
    public void exception(Exception exception) {

        log.error("Service Exception : {}", exception.getMessage());
    }
}
