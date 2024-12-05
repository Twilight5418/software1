package org.twilight.software1.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 */
@Data
@TableName("user")
public class User {
    @TableId
    private String id;

    private String name;
    private String password;
    private String role; // "student", "teacher", "manager", "reviewer"
}