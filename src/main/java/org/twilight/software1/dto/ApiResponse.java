package org.twilight.software1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应包装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int code;          // 状态码
    private String message;    // 响应消息
    private T data;            // 响应数据

    // 状态码常量
    public static final int SUCCESS_CODE = 200;
    public static final int BAD_REQUEST_CODE = 400;
    public static final int NOT_FOUND_CODE = 404;
    // 可以根据需要添加更多状态码

    // 静态工厂方法：成功响应
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(SUCCESS_CODE, message, data);
    }

    // 静态工厂方法：错误响应
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
