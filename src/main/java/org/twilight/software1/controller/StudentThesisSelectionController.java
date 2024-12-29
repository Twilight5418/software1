package org.twilight.software1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twilight.software1.dto.ApiResponse;
import org.twilight.software1.entity.StudentThesisSelection;
import org.twilight.software1.service.StudentThesisSelectionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student-selections")
public class StudentThesisSelectionController {

    @Autowired
    private StudentThesisSelectionService studentThesisSelectionService;

    /**
     * 学生选择题目
     *
     * @param selection 包含学生和选题关联信息的对象
     * @return 响应实体，包含操作结果的消息
     */
    @PostMapping("/select")
    public ResponseEntity<?> selectThesis(@RequestBody StudentThesisSelection selection) {
        StudentThesisSelection savedSelection = studentThesisSelectionService.selectThesis(selection);
        if (savedSelection!= null) {
            Map<String, String> responseData = new HashMap<>();
            responseData.put("studentId", savedSelection.getStudentId());
            responseData.put("thesisProposalId", savedSelection.getThesisProposalId());
            responseData.put("message", "Thesis selected successfully!");
            return ResponseEntity.ok(responseData);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to select thesis.");
        }
    }

    /**
     * 根据学生 ID 获取该学生所选的题目
     *
     * @param studentId 学生的唯一标识（关联到 User 表中的 ID）
     * @return 包含所选题目列表的响应实体，如果没有则返回相应提示信息
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<List<StudentThesisSelection>>> getSelectedThesesByStudentId(
            @PathVariable String studentId) {
        List<StudentThesisSelection> selectedTheses = studentThesisSelectionService.getSelectedThesesByStudentId(studentId);
        if (selectedTheses!= null &&!selectedTheses.isEmpty()) {
            ApiResponse<List<StudentThesisSelection>> response = ApiResponse.success(
                    "Selected theses retrieved successfully!",
                    selectedTheses
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<List<StudentThesisSelection>> response = ApiResponse.error(
                    ApiResponse.NOT_FOUND_CODE,
                    "No theses selected by this student."
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}