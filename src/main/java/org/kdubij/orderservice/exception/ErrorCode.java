package org.kdubij.orderservice.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ErrorCode {
    @JsonProperty("InternalServerError")
    INTERNAL_SERVER_ERROR,

    @JsonProperty("FieldValidationError")
    VALIDATION_ERROR,

    @JsonProperty("MethodNotAllowedError")
    METHOD_NOT_ALLOWED_ERROR,

    @JsonProperty("ResourceNotFoundError")
    RESOURCE_NOT_FOUND_ERROR,

}
