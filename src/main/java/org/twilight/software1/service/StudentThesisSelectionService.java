package org.twilight.software1.service;

import org.twilight.software1.entity.StudentThesisSelection;
import java.util.List;

public interface StudentThesisSelectionService {
    /**
     * 选择老师题目
     * @param selection 学生和题目id
     * @return 学生导师选择信息
     */
    StudentThesisSelection selectThesis(StudentThesisSelection selection);
    /**
     * 获取学生选择的题目列表
     * @param studentId 学生ID
     * @return 学生选择的题目列表
     */
    List<StudentThesisSelection> getSelectedThesesByStudentId(String studentId);
}