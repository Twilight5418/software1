package org.twilight.software1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twilight.software1.entity.StudentThesisSelection;
import org.twilight.software1.mapper.StudentThesisSelectionMapper;
import org.twilight.software1.service.StudentThesisSelectionService;

import java.util.List;

@Service
public class StudentThesisSelectionServiceImpl implements StudentThesisSelectionService {

    @Autowired
    private StudentThesisSelectionMapper studentThesisSelectionMapper;

    @Override
    public StudentThesisSelection selectThesis(StudentThesisSelection selection) {
        return studentThesisSelectionMapper.insert(selection) > 0? selection : null;
    }

    @Override
    public List<StudentThesisSelection> getSelectedThesesByStudentId(String studentId) {
        return studentThesisSelectionMapper.selectList(
                new QueryWrapper<StudentThesisSelection>().eq("student_id", studentId)
        );
    }

}