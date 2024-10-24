package com.learnandgrow.expensetrackerapi.exceptions;

import com.learnandgrow.expensetrackerapi.entity.ErrorObject;
import com.learnandgrow.expensetrackerapi.entity.Expense;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(value = { ItemAlreadyExistsException.class })
    public ResponseEntity<ErrorObject> handleItemExistsException(ItemAlreadyExistsException itemAlreadyExistsException, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(itemAlreadyExistsException.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorObject> handleGeneralException(Exception exception, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String , Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());

        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                        .map(x -> x.getDefaultMessage())
                                .collect(Collectors.toList());

        body.put("messages", errors);
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
}
