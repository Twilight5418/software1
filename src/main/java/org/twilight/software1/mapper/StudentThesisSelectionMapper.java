package org.twilight.software1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.twilight.software1.entity.StudentThesisSelection;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentThesisSelectionMapper extends BaseMapper<StudentThesisSelection> {
    // 可以在这里添加自定义的 SQL 方法，如果有需要的话
    // 例如根据学生 ID 和题目 ID 检查是否已存在选题记录等方法
}