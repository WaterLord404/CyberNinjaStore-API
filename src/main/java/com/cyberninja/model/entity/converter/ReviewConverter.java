package com.cyberninja.model.entity.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Review;
import com.cyberninja.model.entity.dto.ReviewDTO;

@Component
public class ReviewConverter {

	public ReviewDTO reviewToReviewDTO(Review review) {
		ReviewDTO dto = new ReviewDTO();

		dto.setCreationDate(review.getCreationDate());
		dto.setDetails(review.getDetails());
		dto.setValue(review.getValue());
		
		return dto;
	}
	
	public Review reviewDTOToReview(ReviewDTO dto) {
		Review review = new Review();

		review.setCreationDate(LocalDateTime.now());
		review.setDetails(dto.getDetails());
		review.setValue(dto.getValue());
		
		return review;
	}
	
	public List<ReviewDTO> reviewsToReviewsDTO(List<Review> reviews) {
		List<ReviewDTO> dtos = new ArrayList<>();
		
		for (Review review : reviews) {
			dtos.add(reviewToReviewDTO(review));
		}
		
		return dtos;
	}
}
