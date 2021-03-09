package com.cyberninja.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DISCOUNTS")
public class Discount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8892199960050575645L;

	private Long id;
	
	
}
