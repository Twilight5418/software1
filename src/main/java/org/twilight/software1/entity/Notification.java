package org.twilight.software1.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 通知实体类
 */
@Data
@TableName("notification")
public class Notification {
    @TableId
    private String id;

    private String title;
    private String content;
    private LocalDateTime publishTime;
    private String publisherId;
}
