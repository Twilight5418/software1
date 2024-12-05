package org.twilight.software1.service.impl;

import org.twilight.software1.entity.Notification;
import org.twilight.software1.mapper.NotificationMapper;
import org.twilight.software1.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    @Transactional
    public boolean createNotification(Notification notification) {
        // 这里应该是将通知信息保存到数据库的逻辑
        return notificationMapper.insert(notification) > 0;
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationMapper.findAllNotifications();
    }
}