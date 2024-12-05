package org.twilight.software1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twilight.software1.dto.ApiResponse;
import org.twilight.software1.dto.NotificationRequest;
import org.twilight.software1.dto.NotificationResponse;
import org.twilight.software1.entity.Notification;
import org.twilight.software1.service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notifications")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest notificationRequest) {
        Notification notification = new Notification();
        notification.setTitle(notificationRequest.getTitle());
        notification.setContent(notificationRequest.getContent());
        notification.setPublishTime(LocalDateTime.now());
        notification.setPublisherId(notificationRequest.getPublisherId());

        boolean success = notificationService.createNotification(notification);
        if (success) {
            NotificationResponse response = new NotificationResponse("Notification sent successfully!", notification);
            return ResponseEntity.ok(response);
        } else {
            NotificationResponse response = new NotificationResponse("Failed to send notification.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/notifications")
    public ResponseEntity<ApiResponse<List<Notification>>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        ApiResponse<List<Notification>> response = new ApiResponse<>(200, "Notifications retrieved successfully", notifications);
        return ResponseEntity.ok(response);
    }
}