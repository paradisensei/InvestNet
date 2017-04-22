package org.singularnost.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Aidar Shaifutdinov.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final T data;

    private final String error;

    public ApiResponse(T data) {
        this.data = data;
        this.error = null;
    }

    public ApiResponse(String error) {
        this.error = error;
        this.data = null;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

}
