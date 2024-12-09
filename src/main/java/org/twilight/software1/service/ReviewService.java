package org.twilight.software1.service;

import org.twilight.software1.entity.Review;
import org.twilight.software1.entity.ThesisProposal;
import java.util.List;

public interface ReviewService {
    /**
     * 审核题目
     * @param thesisId
     * @param reviewerName
     * @param isApproved
     */
    void approveThesisProposal(String thesisId, String reviewerName, boolean isApproved);

    /**
     *得到所有的审核结果
     * @return
     */
   List<Review> getAllReviews();
   /**
   * 得到所有通过审核的题目
   * @return
    */
   List<Review> getApprovedReviews();
}