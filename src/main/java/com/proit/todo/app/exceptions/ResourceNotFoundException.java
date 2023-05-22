package com.proit.todo.app.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author raqib91
 */
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName,
                                     String fieldName,
                                     Object fieldValue) {
        super(fieldName == null ? String.format("NO %s TO SHOW", resourceName) :
                String.format("%s NOT FOUND WITH %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
