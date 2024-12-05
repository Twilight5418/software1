package org.twilight.software1.dto;

import lombok.Data;

/**
 * 通知创建请求对象
 */
@Data
public class NotificationRequest {
    private String title;
    private String content;
    private String publisherId;
}