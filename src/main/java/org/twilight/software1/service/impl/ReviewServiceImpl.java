package org.twilight.software1.service.impl;

import org.twilight.software1.entity.Review;
import org.twilight.software1.entity.ThesisProposal;
import org.twilight.software1.mapper.ReviewMapper;
import org.twilight.software1.mapper.ThesisProposalMapper;
import org.twilight.software1.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ThesisProposalMapper thesisProposalMapper;

    @Override
    @Transactional
    public void approveThesisProposal(String thesisId, String reviewerName, boolean isApproved) {
        Review review = new Review();
        review.setReviewId(thesisId);
        review.setThesisTitle(thesisProposalMapper.getThesisTitleById(thesisId));
        review.setTeacherName(thesisProposalMapper.getTeacherNameById(thesisId));
        review.setReviewerName(reviewerName);
        review.setIsApproved(isApproved);
        reviewMapper.insert(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewMapper.selectAllReviews();
    }

    @Override
    public List<Review> getApprovedReviews() {
        return reviewMapper.selectApprovedReviews();
    }
}