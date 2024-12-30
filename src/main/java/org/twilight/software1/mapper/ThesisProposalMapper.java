package org.twilight.software1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.twilight.software1.entity.ThesisProposal;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface ThesisProposalMapper extends BaseMapper<ThesisProposal> {
    @Select("SELECT thesis_title FROM thesis_proposal WHERE id = #{thesisId}")
    String getThesisTitleById(String thesisId);

    @Select("SELECT teacher_name FROM thesis_proposal WHERE id = #{thesisId}")
    String getTeacherNameById(String thesisId);

    @Select("SELECT * FROM thesis_proposal tp LEFT JOIN review r ON tp.id = r.review_id")
    @Results({
            @Result(property = "id", column = "id"),
            // 映射ThesisProposal的其他属性
            @Result(property = "review.reviewId", column = "review_id"),
            // 映射Review的其他属性
    })
    List<ThesisProposal> getAllProposalsWithReview();
}