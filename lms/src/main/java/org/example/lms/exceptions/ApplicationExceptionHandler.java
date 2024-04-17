package org.example.lms.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.lms.dto.outbound.ApiErrorResponse;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> handleApplicationException(
            final ApplicationException exception, final HttpServletRequest request
    ) {
        var guid = UUID.randomUUID().toString();
        log.error(
                String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()),
                exception
        );
        var response = new ApiErrorResponse(
                guid,
                exception.getErrorCode(),
                exception.getMessage(),
                exception.getHttpStatus().value(),
                exception.getHttpStatus().name(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownException(
            final Exception exception, final HttpServletRequest request
    ) {
        var guid = UUID.randomUUID().toString();
        log.error(
                String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()),
                exception
        );
        var response = new ApiErrorResponse(
                guid,
                HTTPStatusCodesEnum.INTERNAL_SERVER_ERROR.getHttpStatusCode(),
                "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> handleValidationException(HandlerMethodValidationException ex, final HttpServletRequest request) {
        var guid = UUID.randomUUID().toString();
        log.error(
                String.format("Error GUID=%s; error message: %s", guid, ex.getMessage()),
                ex
        );

        List<List<String>> errors =  ex.getAllValidationResults().stream().map(e -> e.getResolvableErrors().stream().map(MessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())).toList();

        ApiErrorResponse response = new ApiErrorResponse(
                UUID.randomUUID().toString(),
                "VALIDATION_FAILURE",
                "Validation failure",
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now()
        );
        response.addError("errors", errors.get(0));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

