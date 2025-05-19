//package com.example.sistemauniversitario.validation;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.stream.Collectors;
//
//@ControllerAdvice
//// Usamos ControllerAdvice en vez de RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
//        String mensaje = ex.getBindingResult().getFieldErrors().stream()
//                .map(error -> error.getField() + ": " + error.getDefaultMessage())
//                .collect(Collectors.joining(", "));
//
//        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), mensaje, request.getRequestURI());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ApiError> handleRuntime(RuntimeException ex, HttpServletRequest request) {
//        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//}