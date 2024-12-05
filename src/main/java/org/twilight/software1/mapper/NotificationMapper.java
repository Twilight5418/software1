package org.twilight.software1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.twilight.software1.entity.Notification;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface NotificationMapper extends BaseMapper<Notification> {

    /**
     * 查询所有通知
     * @return 返回所有通知的列表
     */
    @Select("SELECT * FROM notification")
    List<Notification> findAllNotifications();
}