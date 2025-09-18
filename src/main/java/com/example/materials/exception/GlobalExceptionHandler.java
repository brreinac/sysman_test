package com.example.materials.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> onNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
        "status", HttpStatus.NOT_FOUND.value(),
        "message", ex.getMessage(),
        "data", null
    ));
}


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String,Object>> onValidation(MethodArgumentNotValidException ex){
    Map<String,String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
        "status", HttpStatus.BAD_REQUEST.value(),
        "message", "Validation failed",
        "data", errors
        ));
}


@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> onIllegalArg(IllegalArgumentException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
        "status", HttpStatus.BAD_REQUEST.value(),
        "message", ex.getMessage(),
        "data", null
    ));
}


@ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> onAny(Exception ex){
    ex.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "message", "Internal server error",
        "data", null
    ));
}
}