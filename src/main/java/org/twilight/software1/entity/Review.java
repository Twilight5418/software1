package org.twilight.software1.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("review")
public class Review {
    @TableId
    private String reviewId;

    private String thesisTitle;
    private String teacherName;
    private String reviewerName;
    private Boolean isApproved;
    // 其他可能需要的字段，如审核意见、审核时间等
}