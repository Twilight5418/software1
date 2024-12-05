package org.twilight.software1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用响应对象
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
}
