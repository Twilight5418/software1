package org.twilight.software1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.twilight.software1.entity.ThesisProposal;
import org.twilight.software1.service.ThesisProposalService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ThesisProposalServiceTest {

    @Autowired
    private ThesisProposalService thesisProposalService;

    @Test
    public void testGetAllProposals() {
        // 调用方法获取结果
        List<ThesisProposal> proposals = thesisProposalService.getAllProposals();
        // 进行断言，先确保不为null
        assertNotNull(proposals);

        // 遍历列表输出相关信息
        for (ThesisProposal proposal : proposals) {
            System.out.println("Thesis Proposal ID: " + proposal.getId());
            System.out.println("Thesis Title: " + proposal.getThesisTitle());
            System.out.println("Teacher Name: " + proposal.getTeacherName());
            // 如果ThesisProposal类中有和Review关联的属性，并且已经正确关联查询获取到了，还可以输出Review相关信息，例如：
            if (proposal.getReview()!= null) {
                System.out.println("is_approved: " + proposal.getReview().getIsApproved());
            }
            System.out.println("---------------------------");
        }
    }
}