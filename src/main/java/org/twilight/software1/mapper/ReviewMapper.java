package org.twilight.software1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.twilight.software1.entity.Review;
import java.util.List;

public interface ReviewMapper extends BaseMapper<Review> {
    /**
     * 查询所有审题记录
     * @return 返回所有 Review 记录的列表
     */
    @Select("SELECT * FROM review")
    List<Review> selectAllReviews();

    /**
     * 查询所有已批准的审题记录
     * @return 返回所有已批准 Review 记录的列表
     */
    @Select("SELECT * FROM review WHERE is_approved = true")
    List<Review> selectApprovedReviews();
}