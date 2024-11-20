package com.accounting.einvoices.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SimpleKeycloakAccount userDetails = (SimpleKeycloakAccount) authentication.getDetails();
        return userDetails.getKeycloakSecurityContext().getToken().getPreferredUsername();
    }

    @Pointcut("within(com.accounting.einvoices.controller.*)")
    public void anyControllerOperation() {};

    @Before("anyControllerOperation()")
    public void beforeAnyControllerOperationAdvice(JoinPoint joinPoint){
        log.info("Before -> Method: {}, User: {}",
                joinPoint.getSignature().toShortString(), getUsername());
    }

    @AfterReturning(value = "anyControllerOperation()", returning = "result")
    public void afterReturningAnyControllerOperationAdvice(JoinPoint joinPoint, Object result){
        log.info("After Returning -> Method: {}, User: {}, Result: {}",
                joinPoint.getSignature(), getUsername(), result.toString());
    }

    @AfterThrowing(value = "anyControllerOperation()", throwing = "exception")
    public void afterThrowingAnyControllerExceptionAdvice(JoinPoint joinPoint, Exception exception){
        log.info("After Throwing -> Method: {}, User: {}, Exception: {}",
                joinPoint.getSignature(), getUsername(), exception.getMessage());
    }


}
