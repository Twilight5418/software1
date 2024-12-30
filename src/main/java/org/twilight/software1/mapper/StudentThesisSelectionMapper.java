package org.twilight.software1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.twilight.software1.entity.StudentThesisSelection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentThesisSelectionMapper extends BaseMapper<StudentThesisSelection> {
    // 可以在这里添加自定义的 SQL 方法，如果有需要的话
    // 例如根据学生 ID 和题目 ID 检查是否已存在选题记录等方法
    @Select("SELECT sts.*, tp.* " +
            "FROM student_thesis_selection sts " +
            "LEFT JOIN thesis_proposal tp ON sts.thesis_proposal_id = tp.id " +
            "WHERE sts.student_id = #{studentId}")
    @Results({
            @Result(property = "id", column = "id"),
            // 映射ThesisProposal的其他属性
            @Result(property = "thesisProposal.teacherName", column = "teacher_name"),
            @Result(property = "thesisProposal.researchInstitute", column = "research_institute"),
            @Result(property = "thesisProposal.education", column = "education"),
            @Result(property = "thesisProposal.targetMajor", column = "target_major"),
            @Result(property = "thesisProposal.contactInfo", column = "contact_info"),
            @Result(property = "thesisProposal.thesisTitle", column = "thesis_title"),
    })
    List<StudentThesisSelection> getSelectedThesesWithProposalInfoByStudentId(String studentId);
}