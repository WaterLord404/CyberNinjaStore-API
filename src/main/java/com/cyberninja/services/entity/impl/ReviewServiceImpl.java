package com.cyberninja.services.entity.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Review;
import com.cyberninja.model.entity.converter.ReviewConverter;
import com.cyberninja.model.entity.dto.ReviewDTO;
import com.cyberninja.model.repository.ReviewRepository;
import com.cyberninja.services.business.ReviewBusinessServiceI;
import com.cyberninja.services.entity.ProductServiceI;
import com.cyberninja.services.entity.ReviewServiceI;

@Service
public class ReviewServiceImpl implements ReviewServiceI {

	@Autowired private ReviewRepository reviewRepo;
	
	@Autowired private ReviewConverter reviewConverter;
	
	@Autowired private ProductServiceI productService;
	
	@Autowired private ReviewBusinessServiceI reviewBService;
	
	/**
	 * Obtiene las review de un producto
	 */
	@Override
	public List<ReviewDTO> getReviews(Long productId) {
		List<ReviewDTO> dtos = reviewConverter.reviewsToReviewsDTO(
				reviewRepo.findReviewByProduct(productService.getProduct(productId)));
		
		if (dtos.isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND);
		}
		
		return dtos;
	}
	
	/**
	 * Crea una review para un producto
	 */
	@Override
	public ReviewDTO addReview(Long productId, ReviewDTO dto) {
		Review review = reviewConverter.reviewDTOToReview(dto);
		
		// Valida la review
		if (!reviewBService.isReviewValid(review)) {
			throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
		}
		
		review.setProduct(productService.getProduct(productId));
		
		reviewRepo.save(review);
		
		return reviewConverter.reviewToReviewDTO(review);
	}

}
