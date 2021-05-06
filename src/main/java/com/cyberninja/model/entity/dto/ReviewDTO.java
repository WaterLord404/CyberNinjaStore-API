package com.cyberninja.model.entity.dto;

import java.time.LocalDateTime;

public class ReviewDTO {
	
	private LocalDateTime creationDate;
	
	private Integer value;
	
	private String details;

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
}
