package com.library.project.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public DuplicateException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("There is already a %s with that %s %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
