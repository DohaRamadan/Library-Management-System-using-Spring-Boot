package org.example.lms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* org.example.lms.controllers.*.*(..)) || " +
            "execution(* org.example.lms.services.impl.*.*(..))")
    public void logPointcut() {}

    @Before("logPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method: " + joinPoint.getSignature().toShortString() + ". Exception message: " + exception.getMessage());
    }
}
