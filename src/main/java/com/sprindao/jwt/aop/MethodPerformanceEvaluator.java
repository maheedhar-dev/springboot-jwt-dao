package com.sprindao.jwt.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodPerformanceEvaluator {
    Logger logger = LoggerFactory.getLogger(MethodPerformanceEvaluator.class);
    @Around("@annotation(com.sprindao.jwt.aop.TimeEvaluator)")
    public Object performanceEvaluator(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        Object objectResponse = proceedingJoinPoint.proceed();
        Long end = System.currentTimeMillis();
        logger.info(" Time taken by Class: {}----Method: {}-------is: {}", className, methodName,(end-start));
        return objectResponse;
    }
}
