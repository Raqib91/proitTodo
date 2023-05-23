package com.proit.todo.app.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author raqib91
 */
@Setter
@Getter
public class ResourceNotModifiedException extends RuntimeException {
    private String operation;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotModifiedException(String operation,
                                        String resourceName,
                                        String fieldName,
                                        Object fieldValue) {
        super(fieldName == null ?
                String.format("COULD NOT %s %s", operation, resourceName) :
                String.format("COULD NOT %s %s WITH %s: %s", operation, resourceName, fieldName, fieldValue));
        this.operation = operation;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
