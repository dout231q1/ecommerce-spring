package com.example.shop.infra;

import com.example.shop.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ErrorResponse> resourceNotFoundHandler(ResourceNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining(" | "));
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    private ResponseEntity<ErrorResponse> notValidURLhandler(NoResourceFoundException exception){
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid URL '" + exception.getResourcePath() + "'. Please check the endpoint." );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<ErrorResponse> methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST,  "'" + exception.getValue() + "' for parameter '" +  exception.getName() + "'. Excepted a valid number.'");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
