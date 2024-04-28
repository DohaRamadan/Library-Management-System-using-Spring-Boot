package org.example.lms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* org.example.lms.controllers.*.*(..)) || " +
            "execution(* org.example.lms.services.*.*(..))")
    public Object logBeforeAndAfter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Entering method: " + proceedingJoinPoint.getSignature().toShortString());

        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        logger.info("Exiting method: " + proceedingJoinPoint.getSignature().toShortString() + ". Execution time: " + stopWatch.getTotalTimeMillis() + "ms");

        return result;
    }

    @AfterThrowing(pointcut = "execution(* org.example.lms.controllers.*.*(..)) || " +
            "execution(* org.example.lms.services.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method: " + joinPoint.getSignature().toShortString() + ". Exception message: " + exception.getMessage());
    }
}
