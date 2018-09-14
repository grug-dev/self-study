package com.kkpa.tutorial.handle;

import java.time.LocalDate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class MyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFound(Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setDate(LocalDate.now());
    exceptionResponse.setMessage(ex.getMessage() + " - " + request.getDescription(false));

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
  }

  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setDate(LocalDate.now());
    exceptionResponse.setMessage(ex.getMessage() + " - " + request.getDescription(false) + " - "
        + ex.getBindingResult().toString());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);

  }

}
