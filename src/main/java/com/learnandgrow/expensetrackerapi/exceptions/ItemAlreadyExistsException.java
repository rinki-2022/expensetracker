package com.learnandgrow.expensetrackerapi.exceptions;

public class ItemAlreadyExistsException extends RuntimeException {

    private static final long serialVersionId = 1L;

    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
