package org.twilight.software1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twilight.software1.dto.ApiResponse;
import org.twilight.software1.dto.ThesisProposalResponse;
import org.twilight.software1.entity.ThesisProposal;
import org.twilight.software1.service.ThesisProposalService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ThesisController {

    @Autowired
    private ThesisProposalService thesisProposalService;

    /**
     * 提交毕业论文申请单
     * @param proposal
     * @return
     */
    @PostMapping("/thesis")
    public ResponseEntity<?> submitThesisProposal(@RequestBody ThesisProposal proposal) {
        ThesisProposal savedProposal = thesisProposalService.submitThesisProposal(proposal);
        if (savedProposal != null) {
            ThesisProposalResponse response = new ThesisProposalResponse(
                "Thesis proposal submitted successfully!",
                savedProposal
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Failed to submit thesis proposal.");
        }
    }
    /**
     * 根据教师姓名查询毕业论文申请单
     * 如果不提供 teacherName 参数，则返回所有申请单
     *
     * @param teacherName 教师姓名（可选）
     * @return 申请单列表
     */
    @GetMapping("/thesis")
    public ResponseEntity<ApiResponse<List<ThesisProposal>>> getProposalsByTeacherName(
            @RequestParam(required = false) String teacherName) {
        List<ThesisProposal> proposals;

        if (teacherName != null && !teacherName.trim().isEmpty()) {
            // 根据教师姓名查询
            proposals = thesisProposalService.getProposalsByTeacherName(teacherName);
            if (proposals != null && !proposals.isEmpty()) {
                ApiResponse<List<ThesisProposal>> response = ApiResponse.success(
                    "Thesis proposals retrieved successfully!",
                    proposals
                );
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<List<ThesisProposal>> response = ApiResponse.error(
                    ApiResponse.NOT_FOUND_CODE,
                    "No thesis proposals found for teacher: " + teacherName
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } else {
            // 查询所有申请单
            proposals = thesisProposalService.getAllProposals();
            if (proposals != null && !proposals.isEmpty()) {
                ApiResponse<List<ThesisProposal>> response = ApiResponse.success(
                    "All thesis proposals retrieved successfully!",
                    proposals
                );
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<List<ThesisProposal>> response = ApiResponse.error(
                    ApiResponse.NOT_FOUND_CODE,
                    "No thesis proposals found."
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }
    }
}
