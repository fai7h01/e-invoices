package com.accounting.einvoices.dto.response.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionWrapper {

    private LocalDateTime timestamp;
    private boolean success;
    private HttpStatus httpStatus;
    private String message;
    private String path;

    private Integer errorCount;
    private List<ValidationExceptionWrapper> validationExceptions;

}
