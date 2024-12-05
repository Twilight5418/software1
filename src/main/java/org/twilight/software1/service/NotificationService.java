package org.twilight.software1.service;

import org.twilight.software1.entity.Notification;

import java.util.List;

public interface NotificationService {
    /**
     * 创建新通知
     * @param notification
     * @return
     */
    boolean createNotification(Notification notification);
    /**
     * 获取所有通知
     * @return
     */
    List<Notification> getAllNotifications();
}