package com.learnandgrow.expensetrackerapi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {

    private String message;
    private Integer errorCode;
    private Date timestamp;
}
