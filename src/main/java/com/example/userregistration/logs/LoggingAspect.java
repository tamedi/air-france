package com.example.userregistration.logs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging method inputs, outputs and processing times.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.userregistration.controller.UserController.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.info("Start method: {}", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("End method: {} with result: {}", joinPoint.getSignature().getName(), result);
        logger.info("Processing time: {} ms", (endTime - startTime));
        return result;
    }
}
