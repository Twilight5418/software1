package org.twilight.software1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.twilight.software1.entity.ThesisProposal;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface ThesisProposalMapper extends BaseMapper<ThesisProposal> {
    @Select("SELECT thesis_title FROM thesis_proposal WHERE id = #{thesisId}")
    String getThesisTitleById(String thesisId);

    @Select("SELECT teacher_name FROM thesis_proposal WHERE id = #{thesisId}")
    String getTeacherNameById(String thesisId);
}