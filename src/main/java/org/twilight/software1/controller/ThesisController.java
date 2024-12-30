package org.twilight.software1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twilight.software1.dto.ApiResponse;
import org.twilight.software1.dto.ThesisProposalResponse;
import org.twilight.software1.entity.ThesisProposal;
import org.twilight.software1.service.ThesisProposalService;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ThesisController {

    @Autowired
    private ThesisProposalService thesisProposalService;

    /**
     * 提交毕业论文申请单
     *
     * @param proposal 包含申请单信息的ThesisProposal对象
     * @return 根据提交结果返回相应的响应信息
     */
    @PostMapping("/thesis")
    public ResponseEntity<?> submitThesisProposal(@RequestBody ThesisProposal proposal) {
        ThesisProposal savedProposal = thesisProposalService.submitThesisProposal(proposal);
        if (savedProposal!= null) {
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
     * 如果不提供teacherName参数，则返回所有申请单
     *
     * @param teacherName 教师姓名（可选）
     * @return 申请单列表相关的响应信息
     */
    @GetMapping("/thesis")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getProposalsByTeacherName(
            @RequestParam(required = false) String teacherName) {
        List<ThesisProposal> proposals;
        ResponseEntity<ApiResponse<List<Map<String, Object>>>> responseEntity;
        try {
            if (teacherName!= null &&!teacherName.trim().isEmpty()) {
                // 根据教师姓名查询
                proposals = thesisProposalService.getProposalsByTeacherName(teacherName);
                if (proposals!= null &&!proposals.isEmpty()) {
                    List<Map<String, Object>> resultList = buildProposalResponseList(proposals);
                    ApiResponse<List<Map<String, Object>>> response = ApiResponse.success(
                            proposals.size() == 1? "Thesis proposal retrieved successfully!" : "Thesis proposals retrieved successfully!",
                            resultList
                    );
                    responseEntity = ResponseEntity.ok(response);
                } else {
                    // 修改此处，返回包含空List<Map<String, Object>>的ApiResponse
                    ApiResponse<List<Map<String, Object>>> response = ApiResponse.error(
                            ApiResponse.NOT_FOUND_CODE,
                            "No thesis proposals found."
                    );
                    responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            } else {
                // 查询所有申请单
                proposals = thesisProposalService.getAllProposals();
                if (proposals!= null &&!proposals.isEmpty()) {
                    List<Map<String, Object>> resultList = buildProposalResponseList(proposals);
                    ApiResponse<List<Map<String, Object>>> response = ApiResponse.success(
                            "All thesis proposals retrieved successfully!",
                            resultList
                    );
                    responseEntity = ResponseEntity.ok(response);
                } else {
                    // 修改此处，返回包含空List<Map<String, Object>>的ApiResponse
                    ApiResponse<List<Map<String, Object>>> response = ApiResponse.error(
                            ApiResponse.NOT_FOUND_CODE,
                            "No thesis proposals found."
                    );
                    responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            }
        } catch (Exception e) {
            // 添加默认返回语句，返回表示错误状态且无数据的ResponseEntity，用于处理异常情况
            ApiResponse<List<Map<String, Object>>> defaultResponse = ApiResponse.error(
                    ApiResponse.BAD_REQUEST_CODE,
                    "An unexpected error occurred while retrieving proposals."
            );
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(defaultResponse);
        }
        return responseEntity;
    }

    /**
     * 构建包含申请单信息（排除 review 属性）及审核结果等的响应数据列表
     *
     * @param proposals 申请单对象列表
     * @return 包含申请单信息及审核结果的 Map 列表
     */
    private List<Map<String, Object>> buildProposalResponseList(List<ThesisProposal> proposals) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (ThesisProposal proposal : proposals) {
            Map<String, Object> resultMap = new HashMap<>();
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(ThesisProposal.class);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    String propertyName = propertyDescriptor.getName();
                    // 排除 "class" 属性以及 "review" 属性（假设 review 是关联 Review 类的属性，这里排除它）
                    if (!"class".equals(propertyName) &&!"review".equals(propertyName)) {
                        resultMap.put(propertyName, propertyDescriptor.getReadMethod().invoke(proposal));
                    }
                }
            } catch (IntrospectionException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
                e.printStackTrace();
            }
            // 添加审核结果相关属性（这里以isApproved为例，你可以根据实际Review类中的更多属性进行扩展）
            if (proposal.getReview()!= null) {
                resultMap.put("isApproved", proposal.getReview().getIsApproved());
            }
            resultList.add(resultMap);
        }
        return resultList;
    }
}