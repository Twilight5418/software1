package org.twilight.software1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 毕业论文提案实体类
 */
@Data
@TableName("thesis_proposal")
public class ThesisProposal {
    @TableId(value = "id", type = IdType.ASSIGN_UUID) // 使用 UUID 作为 ID 生成策略
    private String id; // 使用 String 作为 ID 类型

    private String teacherName;
    private String researchInstitute;
    private String education;
    private Integer age;
    private String targetMajor;
    private String contactInfo;
    private String thesisTitle;
    private Review review;// 关联 Review 实体类
}