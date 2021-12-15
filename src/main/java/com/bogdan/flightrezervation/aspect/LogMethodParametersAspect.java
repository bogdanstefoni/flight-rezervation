package com.bogdan.flightrezervation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMethodParametersAspect {

    private final Logger logger = LoggerFactory.getLogger(LogMethodParametersAspect.class);

    @Around("@annotation(com.bogdan.flightrezervation.annotation.LogMethodParameters)")
    public Object logMethodParameters(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        CodeSignature signature = (CodeSignature) proceedingJoinPoint.getSignature();

        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = proceedingJoinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            logger.info("Parameter name: " + parameterNames[i] + ". Parameter value: " + parameterValues[i]);
        }

        return proceedingJoinPoint.proceed();
    }
}
