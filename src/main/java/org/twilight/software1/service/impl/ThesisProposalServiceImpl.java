package org.twilight.software1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.twilight.software1.entity.ThesisProposal;
import org.twilight.software1.mapper.ThesisProposalMapper;
import org.twilight.software1.service.ThesisProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThesisProposalServiceImpl implements ThesisProposalService {

    @Autowired
    private ThesisProposalMapper thesisProposalMapper;

    @Override
    @Transactional
    public ThesisProposal submitThesisProposal(ThesisProposal proposal) {
        int result = thesisProposalMapper.insert(proposal);
        if (result > 0) {
            return proposal; // 插入成功后，proposal 对象会包含生成的 ID
        } else {
            return null; // 插入失败
        }
    }

    @Override
    public List<ThesisProposal> getProposalsByTeacherName(String teacherName) {
        QueryWrapper<ThesisProposal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_name", teacherName); // 假设数据库字段为 teacher_name
        return thesisProposalMapper.selectList(queryWrapper);
    }

    @Override
    public List<ThesisProposal> getAllProposals() {
        return thesisProposalMapper.selectList(null); // 查询所有记录
    }
}