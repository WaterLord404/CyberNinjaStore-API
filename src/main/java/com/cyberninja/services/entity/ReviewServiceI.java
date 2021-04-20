package com.cyberninja.services.entity;

import java.util.List;

import com.cyberninja.model.entity.dto.ReviewDTO;

public interface ReviewServiceI {

	List<ReviewDTO> getReviews(Long productId);

	ReviewDTO addReview(Long productId, ReviewDTO dto);

}
