package org.example.lms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("execution(* org.example.lms.controllers.*.*(..)) || " +
            "execution(* org.example.lms.services.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().toShortString());
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "execution(* org.example.lms.controllers.*.*(..)) || " +
            "execution(* org.example.lms.services.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        Long start = startTime.get();
        if (start != null) {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - start;
            logger.info("Exiting method: " + joinPoint.getSignature().toShortString() + ". Execution time: " + executionTime + "ms");
            startTime.remove();
        } else {
            logger.warn("Exiting method: " + joinPoint.getSignature().toShortString() + ". Could not determine execution time.");
        }
    }

    @AfterThrowing(pointcut = "execution(* org.example.lms.controllers.*.*(..)) || " +
            "execution(* org.example.lms.services.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method: " + joinPoint.getSignature().toShortString() + ". Exception message: " + exception.getMessage());
        startTime.remove();
    }
}
