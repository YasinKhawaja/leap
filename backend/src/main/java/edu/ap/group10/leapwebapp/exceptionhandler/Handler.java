package edu.ap.group10.leapwebapp.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.ap.group10.leapwebapp.exceptions.LoginException;
import edu.ap.group10.leapwebapp.exceptions.RegisterException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ LoginException.class, AuthenticationException.class, RegisterException.class })
    protected ResponseEntity<Object> handleLoginException(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);
        Object resBody = e.getMessage();
        return handleExceptionInternal(e, resBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleMyException(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);
        Object resBody = "Oops, something went wrong!";
        return handleExceptionInternal(e, resBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
