package com.userTest.app.ws.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.userTest.app.ws.util.exception.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Aspect
@Component
public class LoggingAspect {

    Logger log = LoggerFactory.getLogger(LoggingAspect.class);


    @Around("execution(* com.userTest.app.ws.controllers.*.*(..))")
    public Object beforeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("==============Start Logging==============");
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info("Method: {} executed in {} ms", joinPoint.getSignature(), executionTime);

        return proceed;
    }


    @AfterReturning(value = "execution(* ccom.userTest.app.ws.controllers.*.*(..))", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {

        log.info("Inputs: ");
        String input = "";
        if (!ObjectUtils.isEmpty(joinPoint.getArgs())) {
            input = JsonUtils.toJsonString(joinPoint.getArgs()[0]);
        }
        log.info(input);
        log.info("Outputs: ");
        String output = "";
        if (!ObjectUtils.isEmpty(returnValue)) {
            output = JsonUtils.toJsonString(((ResponseEntity<?>) returnValue).getBody());
        }
        log.info(output);
    }


    @AfterThrowing(value = "execution(* com.userTest.app.ws.controllers.*.*(..))", throwing = "e")
    public void afterThrowingAccessDeniedException(JoinPoint joinPoint, Exception e) throws JsonProcessingException {
        log.info("Inputs: ");
        String input = "";
        if (!ObjectUtils.isEmpty(joinPoint.getArgs())) {
            input = JsonUtils.toJsonString(joinPoint.getArgs()[0]);
        }
        log.info(input);

        log.info("Outputs: ");
        log.info(e.getMessage());
    }

    @After("execution(* com.userTest.app.ws.controllers.*.*(..))")
    public void afterCompletion() {

        log.info("==============End Logging==============");
    }
}