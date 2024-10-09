package com.learnandgrow.expensetrackerapi.exceptions;

import com.learnandgrow.expensetrackerapi.entity.ErrorObject;
import com.learnandgrow.expensetrackerapi.entity.Expense;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<ErrorObject> handleExpenseNotFpundException(ResourceNotFoundException expenseNotFoundException, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(expenseNotFoundException.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    public ResponseEntity<ErrorObject> handleExpenseNotFpundException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(methodArgumentTypeMismatchException.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorObject> handleGeneralException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage("Something went wrong");
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
