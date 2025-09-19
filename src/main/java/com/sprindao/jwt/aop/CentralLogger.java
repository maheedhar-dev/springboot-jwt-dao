package com.sprindao.jwt.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CentralLogger {

    Logger logger = LoggerFactory.getLogger(CentralLogger.class);

    //@Pointcut("execution(* com.sprindao.jwt.controller..*(..)) || execution(* com.sprindao.jwt.service..*(..))")
    @Pointcut("execution(* com.sprindao.jwt.controller..*(..))")
    public void pointcutMethod(){
    }

    @Around( "pointcutMethod()")
    public Object methodLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        Object[] argsArray =  proceedingJoinPoint.getArgs();
        logger.info("Class: {}----Method: {}-------Request Args: {}", className, methodName, objectMapper.writeValueAsString(argsArray));
        Object objectResponse = proceedingJoinPoint.proceed();
        logger.info("Class: {}----Method: {}-------Response Args: {}", className, methodName, objectMapper.writeValueAsString(objectResponse));
        return objectResponse;
    }
}
