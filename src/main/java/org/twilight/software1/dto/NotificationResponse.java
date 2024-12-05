package org.twilight.software1.dto;

import lombok.Data;
import org.twilight.software1.entity.Notification;

@Data
public class NotificationResponse {
    private String message;
    private Notification notification;

    public NotificationResponse(String message, Notification notification) {
        this.message = message;
        this.notification = notification;
    }
}