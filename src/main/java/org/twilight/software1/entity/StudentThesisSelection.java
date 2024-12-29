package org.twilight.software1.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("student_thesis_selection")
public class StudentThesisSelection {
    @TableId
    private Long id;

    private String studentId; // 对应 User 表中的 id，通过此字段关联学生用户
    private String thesisProposalId; // 对应 ThesisProposal 表中的相关标识字段，关联具体选题

    // 可以添加其他相关属性，比如选择时间等

    // 构造函数、Getter 和 Setter 方法等
    public StudentThesisSelection() {
    }

    public StudentThesisSelection(String studentId, String thesisProposalId) {
        this.studentId = studentId;
        this.thesisProposalId = thesisProposalId;
    }
}