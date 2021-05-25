package com.cyberninja.services.entity;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.cyberninja.model.entity.dto.ReviewDTO;

public interface ReviewServiceI {

	List<ReviewDTO> getReviews(Long productId);

	ReviewDTO addReview(Authentication auth, Long productId, ReviewDTO dto);

}
