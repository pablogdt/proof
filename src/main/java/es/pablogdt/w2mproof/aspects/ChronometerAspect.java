package es.pablogdt.w2mproof.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class ChronometerAspect {
    @Around("@annotation(es.pablogdt.w2mproof.annotations.Chronometer)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        log.debug(String.format("Total time spent on calling %s: %d ms", joinPoint.getSignature().getName(), totalTime));
        return proceed;
    }
}
