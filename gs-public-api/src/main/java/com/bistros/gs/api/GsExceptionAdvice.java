package com.bistros.gs.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;


@RestControllerAdvice
public class GsExceptionAdvice {

  final static String ERROR_STRING = "Error";

  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected String validationException(HttpServletRequest req, ValidationException e) {
    if (e.getMessage() != null) {
      return e.getMessage();
    }
    return ERROR_STRING;
  }


}
