package org.kdubij.orderservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@ControllerAdvice
class GlobalExceptionHandler {

    private static final String EXCEPTION_LOG_MESSAGE = "Handling exception {type={}, message={}}";

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ResourceNotFoundException exception) {
        log.warn(EXCEPTION_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .code(ErrorCode.RESOURCE_NOT_FOUND_ERROR)
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleInvalidValueConversion(MethodArgumentTypeMismatchException exception) {
        log.warn(EXCEPTION_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .code(ErrorCode.VALIDATION_ERROR)
                        .message(String.format("Validation error occurred for parameter: %s", exception.getName()))
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ErrorResponse.builder()
                        .message("Method not allowed")
                        .code(ErrorCode.METHOD_NOT_ALLOWED_ERROR)
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
        log.warn(EXCEPTION_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());
        String message = String.format("Validation error occurred in fields: %s", getErrorString(exception.getBindingResult()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(message)
                        .code(ErrorCode.VALIDATION_ERROR)
                        .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ValidationException exception) {
        log.warn(EXCEPTION_LOG_MESSAGE, exception.getClass().getSimpleName(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .code(ErrorCode.VALIDATION_ERROR)
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(Throwable throwable) {
        log.error("Handling internal exception, detailed stacktrace: ", throwable);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .code(ErrorCode.INTERNAL_SERVER_ERROR)
                        .message("Unexpected server error")
                        .build());
    }

    private String getErrorString(BindingResult bindingResult) {
        Stream<String> fieldErrors = bindingResult.getFieldErrors().stream().map(FieldError::getField);
        Stream<String> objectErrors = bindingResult.getGlobalErrors().stream().map(ObjectError::getObjectName);
        return Stream.concat(fieldErrors, objectErrors).sorted().collect(Collectors.joining(", "));
    }
}
