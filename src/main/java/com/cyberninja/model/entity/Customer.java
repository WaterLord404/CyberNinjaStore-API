package com.cyberninja.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cyberninja.security.model.entity.User;

@Entity
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7781152538181773346L;

	private Long id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String telephone;
	
	private String region;
	
	private String province;
	
	private String locality;
	
	private String postalCode;
	
	private String address;

	private LocalDate lastPurchase;

	private List<Order> orders;
	
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTOMER_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SURNAME", nullable = false)
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "EMAIL", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "TELEPHONE", nullable = false)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "REGION", nullable = false)
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "PROVINCE", nullable = false)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "LOCALITY", nullable = false)
	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	@Column(name = "POSTAL_CODE", nullable = false)
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "ADDRESS", nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "LAST_PURCHASE_DATE")
	public LocalDate getLastPurchase() {
		return lastPurchase;
	}

	public void setLastPurchase(LocalDate lastPurchase) {
		this.lastPurchase = lastPurchase;
	}

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
