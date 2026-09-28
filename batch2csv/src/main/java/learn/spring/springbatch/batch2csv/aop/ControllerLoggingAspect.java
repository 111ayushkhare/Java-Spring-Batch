package learn.spring.springbatch.batch2csv.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void before(JoinPoint joinPoint) {
        log.info("========== CONTROLLER START ==========");
        log.info("Class  : {}", joinPoint.getTarget().getClass().getSimpleName());
        log.info("Method : {}", joinPoint.getSignature().getName());
        log.info("Args   : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "within(@org.springframework.web.bind.annotation.RestController *)",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("Response : {}", result);
        log.info("=========== CONTROLLER END ===========");
    }
}
