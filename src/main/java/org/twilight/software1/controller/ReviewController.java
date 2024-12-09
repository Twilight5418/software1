package org.twilight.software1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twilight.software1.dto.ApiResponse;
import org.twilight.software1.entity.Review;
import org.twilight.software1.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 提交审题结果
     *
     * @param review 包含审题结果的 Review 对象
     * @return 响应实体，包含操作结果的消息
     */
    @PostMapping("/submit")
    public ResponseEntity<?> submitReview(@RequestBody Review review) {
        reviewService.approveThesisProposal(review.getReviewId(), review.getReviewerName(), review.getIsApproved());
        return ResponseEntity.ok("Review submitted successfully!");
    }

    /**
     * 获取所有审题结果
     *
     * @return 包含所有审题结果的列表
     */
    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    /**
     * 获取所有已批准的审题结果
     *
     * @return 包含所有已批准审题结果的列表
     */
    @GetMapping("/approved")
    public ResponseEntity<List<Review>> getApprovedReviews() {
        List<Review> approvedReviews = reviewService.getApprovedReviews();
        return ResponseEntity.ok(approvedReviews);
    }
}