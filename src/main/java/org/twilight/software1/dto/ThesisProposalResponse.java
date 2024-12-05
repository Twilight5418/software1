package org.twilight.software1.dto;

import org.twilight.software1.entity.ThesisProposal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应 DTO，用于封装消息和提案数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThesisProposalResponse {
    private String message;
    private ThesisProposal proposal;
}
