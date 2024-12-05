package org.twilight.software1.service;

import org.twilight.software1.entity.ThesisProposal;

import java.util.List;

public interface ThesisProposalService {
    /**
     * 提交论文申请
     * @param proposal 论文申请实体
     * @return 是否提交成功
     */
    ThesisProposal submitThesisProposal(ThesisProposal proposal);
    // 其他业务方法...
    /**
     * 根据教师姓名查询毕业论文申请单
     *
     * @param teacherName 教师姓名
     * @return 申请单列表
     */
    List<ThesisProposal> getProposalsByTeacherName(String teacherName);
    /**
     * 查询所有毕业论文申请单
     *
     * @return 申请单列表
     */
    List<ThesisProposal> getAllProposals();
}