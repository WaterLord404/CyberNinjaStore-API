package com.cyberninja.model.entity.dto;

import java.time.LocalDate;

public class ReviewDTO {
	
	private LocalDate creationDate;
	
	private Integer value;
	
	private String details;

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
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
