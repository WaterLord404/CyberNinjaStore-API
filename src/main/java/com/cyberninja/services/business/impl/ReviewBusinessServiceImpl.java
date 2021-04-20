package com.cyberninja.services.business.impl;

import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Review;
import com.cyberninja.services.business.ReviewBusinessServiceI;

@Service
public class ReviewBusinessServiceImpl implements ReviewBusinessServiceI {

	@Override
	public Boolean isReviewValid(Review review) {
		if (review.getValue() >= 1 && 
			review.getValue() <= 5 &&
			review.getDetails().length() >= 10) {
			
			return true;
		}
		return false;
	}

}
