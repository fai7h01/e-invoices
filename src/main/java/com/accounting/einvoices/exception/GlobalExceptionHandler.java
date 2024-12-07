package com.accounting.einvoices.exception;

import com.accounting.einvoices.dto.response.ExceptionWrapper;
import com.accounting.einvoices.dto.response.ValidationExceptionWrapper;
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
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
//    public ResponseEntity<ExceptionWrapper> handleGenericExceptions(Throwable exception) {
//        log.error(exception.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ExceptionWrapper.builder()
//                        .success(false)
//                        .message("Action failed: An error occurred!")
//                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .timestamp(LocalDateTime.now())
//                        .build());
//    }

    @ExceptionHandler({UserNotFoundException.class, ClientVendorNotFoundException.class, CategoryNotFoundException.class, ProductNotFoundException.class,
            InvoiceProductNotFoundException.class})
    public ResponseEntity<ExceptionWrapper> handleNotFoundExceptions(Throwable exception) {
        log.error(exception.getMessage());
        ExceptionWrapper exceptionWrapper = ExceptionWrapper.builder()
                .success(false)
                .message(exception.getMessage() + "\n\n>> Stack trace: " + Arrays.toString(exception.getStackTrace()))
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);
    }


    @ExceptionHandler({ProductCannotBeDeletedException.class, ClientCannotBeDeletedException.class})
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


    @ExceptionHandler({UserAlreadyExistsException.class, ClientVendorAlreadyExistsException.class, CategoryAlreadyExistsException.class, ProductAlreadyExistsException.class})
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
