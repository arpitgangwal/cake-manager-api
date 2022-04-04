package com.waracle.cakemgr.exception.handler;


import com.waracle.cakemgr.exception.ValidationFailedResponse;
import com.waracle.cakemgr.exception.ViolationErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ValidationFailedResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationFailedResponse error = new ValidationFailedResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(new ViolationErrors(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        log.error("Bad Input Exception Occured",error);
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ValidationFailedResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationFailedResponse error = new ValidationFailedResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new ViolationErrors(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        log.error("Bad Input Exception Occured",error);
        return error;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    protected ValidationFailedResponse jwtValidationError(BadCredentialsException ex) {
        ValidationFailedResponse error = new ValidationFailedResponse();
        log.error(ex.getCause().toString());
        ex.printStackTrace();
         error.getViolations().add(new ViolationErrors("Bad Credentials Exception", ex.getMessage()));
         return error;
    }

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleGeneralException(
            RuntimeException ex, WebRequest request) {
        log.error(ex.getCause().toString());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ViolationErrors("Internal Server Error", ex.getMessage()));

    }
}
