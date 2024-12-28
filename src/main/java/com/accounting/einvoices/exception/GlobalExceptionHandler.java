package com.accounting.einvoices.exception;

import com.accounting.einvoices.dto.response.ExceptionWrapper;
import com.accounting.einvoices.dto.response.ValidationExceptionWrapper;
import com.accounting.einvoices.exception.category.CategoryAlreadyExistsException;
import com.accounting.einvoices.exception.category.CategoryCannotBeDeletedException;
import com.accounting.einvoices.exception.category.CategoryNotFoundException;
import com.accounting.einvoices.exception.client.ClientCannotBeDeletedException;
import com.accounting.einvoices.exception.client.ClientVendorAlreadyExistsException;
import com.accounting.einvoices.exception.client.ClientVendorNotFoundException;
import com.accounting.einvoices.exception.company.CompanyAlreadyExistsException;
import com.accounting.einvoices.exception.company.CompanyNotFoundException;
import com.accounting.einvoices.exception.invoice.InvoiceNotFoundException;
import com.accounting.einvoices.exception.invoice.InvoiceProductNotFoundException;
import com.accounting.einvoices.exception.product.ProductAlreadyExistsException;
import com.accounting.einvoices.exception.product.ProductCannotBeDeletedException;
import com.accounting.einvoices.exception.product.ProductLowLimitAlertException;
import com.accounting.einvoices.exception.product.ProductNotFoundException;
import com.accounting.einvoices.exception.token.TokenNotFoundException;
import com.accounting.einvoices.exception.token.TokenNotValidException;
import com.accounting.einvoices.exception.user.UserAlreadyExistsException;
import com.accounting.einvoices.exception.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    public ResponseEntity<ExceptionWrapper> handleGenericExceptions(Throwable exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionWrapper.builder()
                        .success(false)
                        .message("Action failed: An error occurred!")
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler({UserNotFoundException.class, InvoiceNotFoundException.class, ClientVendorNotFoundException.class, CategoryNotFoundException.class,
            ProductNotFoundException.class, InvoiceProductNotFoundException.class, CompanyNotFoundException.class, DataIsNotPresentException.class,
            TokenNotFoundException.class, TokenNotValidException.class})
    public ResponseEntity<ExceptionWrapper> handleNotFoundExceptions(Throwable exception) {
        log.error(exception.getMessage());
        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .success(false)
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);
    }


    @ExceptionHandler({ProductCannotBeDeletedException.class, ClientCannotBeDeletedException.class, CategoryCannotBeDeletedException.class})
    public ResponseEntity<ExceptionWrapper> cannotBeDeletedException(Throwable exception) {
        log.error(exception.getMessage());
        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .success(false)
                .message(exception.getMessage())
                .httpStatus(HttpStatus.CONFLICT)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionWrapper);
    }


    @ExceptionHandler(ProductLowLimitAlertException.class)
    public ResponseEntity<ExceptionWrapper> lowLimitAlertException(Throwable exception) {
        log.error(exception.getMessage());
        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .success(false)
                .message(exception.getMessage())
                .httpStatus(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(exceptionWrapper);
    }


    @ExceptionHandler({UserAlreadyExistsException.class, ClientVendorAlreadyExistsException.class, CategoryAlreadyExistsException.class,
            ProductAlreadyExistsException.class, CompanyAlreadyExistsException.class})
    public ResponseEntity<ExceptionWrapper> handleConflictExceptions(Throwable exception) {
        log.error(exception.getMessage());
        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .success(false)
                .message(exception.getMessage())
                .httpStatus(HttpStatus.CONFLICT)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionWrapper);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionWrapper> handleAccessExceptions(Throwable exception) {
        log.error(exception.getMessage());
        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .success(false)
                .message(exception.getMessage())
                .httpStatus(HttpStatus.FORBIDDEN)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionWrapper);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionWrapper> handleValidationExceptions(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .success(false)
                .message("Invalid Input(s)")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();

        List<ValidationExceptionWrapper> validationExceptions = collectValidationExceptions(exception);

        exceptionWrapper.setValidationExceptions(validationExceptions);
        exceptionWrapper.setErrorCount(validationExceptions.size());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionWrapper);

    }

    private List<ValidationExceptionWrapper> collectValidationExceptions(MethodArgumentNotValidException exception) {

        List<ValidationExceptionWrapper> validationExceptions = new ArrayList<>();

        for (ObjectError error : exception.getBindingResult().getAllErrors()) {

            String fieldName = ((FieldError) error).getField();
            Object rejectedValue = ((FieldError) error).getRejectedValue();
            String errorMessage = error.getDefaultMessage();

            ValidationExceptionWrapper validationException = ValidationExceptionWrapper.builder()
                    .errorField(fieldName)
                    .rejectedValue(rejectedValue)
                    .reason(errorMessage)
                    .build();

            validationExceptions.add(validationException);

        }

        return validationExceptions;

    }

}
