package com.userTest.app.ws.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.userTest.app.ws.util.exception.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Aspect
@Component
public class LoggingAspect {

    Logger log = LoggerFactory.getLogger(LoggingAspect.class);


    @Around("execution(* com.userTest.app.ws.controllers.*.*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("==============Start Logging==============");
        long start = System.currentTimeMillis();

        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } catch (Exception e) {
            logException(joinPoint, e);
            throw e;
        }

        long executionTime = System.currentTimeMillis() - start;

        log.info("Method: {} executed in {} ms", joinPoint.getSignature(), executionTime);

        log.info("==============End Logging==============");

        return proceed;
    }

    private void logException(JoinPoint joinPoint, Exception e) throws JsonProcessingException {
        log.info("Inputs: ");
        String input = "";
        if (!ObjectUtils.isEmpty(joinPoint.getArgs())) {
            input = JsonUtils.toJsonString(joinPoint.getArgs()[0]);
        }
        log.info(input);
        log.info("Exception: {}", e.getMessage());
    }

}