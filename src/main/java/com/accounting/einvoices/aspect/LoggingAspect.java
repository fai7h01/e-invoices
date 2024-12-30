package com.accounting.einvoices.aspect;

import com.accounting.einvoices.exception.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Profile("dev")
public class LoggingAspect {

    private String getUsername(){
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Check if authentication is null or if the user is not authenticated
            if (authentication == null || !authentication.isAuthenticated()
                    || authentication instanceof AnonymousAuthenticationToken) {
                throw new UserNotFoundException("No User");
            }

            // Ensure authentication details are available
            if (authentication.getDetails() instanceof SimpleKeycloakAccount userDetails) {
                return userDetails.getKeycloakSecurityContext().getToken().getPreferredUsername();
            }
        } catch (RuntimeException e) {
            log.error("No user");
        }

        return " ";
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
