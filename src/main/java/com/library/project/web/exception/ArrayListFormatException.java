package com.library.project.web.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ArrayListFormatException extends RuntimeException{
    private String resourceName;
    private List<String> fieldName;

    public ArrayListFormatException(List<String> fieldName, String resourceName) {
        super(String.format("%s format is incorrect: %s",resourceName,fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
