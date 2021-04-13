package com.intentsg.service.user.exeption;

import com.intentsg.model.exeptions.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementExeption.class)
    public final ResponseEntity<Object> handleNotExistException(NoSuchElementExeption exception) {
        return buildExceptionBody(exception, NOT_FOUND);
    }
    @ExceptionHandler(WrongUserNameExeption.class)
    public final ResponseEntity<Object> handleNotExistException(WrongUserNameExeption exception) {
        return buildExceptionBody(exception, CONFLICT);
    }
    @ExceptionHandler(WrongPasswordExeption.class)
    public final ResponseEntity<Object> handleNotExistException(WrongPasswordExeption exception) {
        return buildExceptionBody(exception, FORBIDDEN);
    }
    @ExceptionHandler(NotExistException.class)
    public final ResponseEntity<Object> handleNotExistException(NotExistException exception) {
        return buildExceptionBody(exception, CONFLICT);
    }
    @ExceptionHandler(AlreadyExistsExeption.class)
    public final ResponseEntity<Object> handleNotExistException(AlreadyExistsExeption exception) {
        return buildExceptionBody(exception, CONFLICT);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleNotExistException(AccessDeniedException exception) {
        return buildExceptionBody(exception, FORBIDDEN);
    }

    private ResponseEntity<Object> buildExceptionBody(Exception exception, HttpStatus httpStatus) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .status(httpStatus.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity
                .status(httpStatus)
                .body(exceptionResponse);
    }
}