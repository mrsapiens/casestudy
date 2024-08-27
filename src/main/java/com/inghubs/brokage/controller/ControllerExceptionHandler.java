package com.inghubs.brokage.controller;

import com.inghubs.brokage.domain.exception.*;
import com.inghubs.brokage.domain.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExists.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseError handleException(CustomerAlreadyExists ex) {
        return new ResponseError(HttpStatus.CONFLICT, "Customer already exists", null);
    }

    @ExceptionHandler(AdminAlreadyExists.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseError handleException(AdminAlreadyExists ex) {
        return new ResponseError(HttpStatus.CONFLICT, "Admin already exists", null);
    }

    @ExceptionHandler(AssetAlreadyExists.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseError handleException(AssetAlreadyExists ex) {
        return new ResponseError(HttpStatus.CONFLICT, "Asset already exists", null);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseError handleException(CustomerNotFoundException ex) {
        return new ResponseError(HttpStatus.NOT_FOUND, "Customer has not found", null);
    }

    @ExceptionHandler(AssetNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseError handleException(AssetNotFoundException ex) {
        return new ResponseError(HttpStatus.NOT_FOUND, "Asset has not found", null);
    }

    @ExceptionHandler(InsufficientCustomerBalance.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseError handleException(InsufficientCustomerBalance ex) {
        return new ResponseError(HttpStatus.BAD_REQUEST, "Customer has insufficient balance", null);
    }

    @ExceptionHandler(InsufficientCustomerAsset.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseError handleException(InsufficientCustomerAsset ex) {
        return new ResponseError(HttpStatus.BAD_REQUEST, "Customer has insufficient asset", null);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseError handleException(OrderNotFoundException ex) {
        return new ResponseError(HttpStatus.NOT_FOUND, "Order has not found", null);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseError handleException(AuthenticationCredentialsNotFoundException ex) {
        return new ResponseError(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleException(RuntimeException ex) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), Arrays.toString(ex.getStackTrace()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleException(UsernameNotFoundException ex) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, "Username not found", null);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseError handleException(BadCredentialsException ex) {
        return new ResponseError(HttpStatus.UNAUTHORIZED, "Invalid username or password", null);
    }


}



