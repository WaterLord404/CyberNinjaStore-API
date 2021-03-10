package com.cyberninja.security.model.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cyberninja.model.Customer;
import com.cyberninja.security.model.entity.enumerated.UserRole;

@Entity
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3479851261461661635L;

	private static final int MAX_AUTH_ATTEMPTS = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(unique = true, name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLES")
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_USERS_ROLES___USER_ID"))
	private Set<UserRole> roles;

	@CreatedDate
	@Column(name = "CREATION_DATE")
	private LocalDateTime creationDate;

	@UpdateTimestamp
	@Column(name = "UPDATE_DATE")
	private LocalDateTime updateDate;

	@Column(name = "DELETE_DATE")
	private LocalDateTime deleteDate;

	@Column(name = "LAST_PASS_CHANGE_DATE")
	private LocalDateTime lastPasswordChange;

	@Column(name = "LOCKED")
	private boolean locked;

	@Column(name = "ENABLED")
	private boolean enabled;

	@Column(name = "AUTH_ATTEMPS")
	private Integer authenticationAttempts;

	@Column(name = "PASS_EXP_DATE")
	private LocalDateTime passwordPolicyExpDate;

	@OneToOne
	@JoinColumn(name = "CUSTOMER_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_USERS__CUSTOMER_ID"))
	private Customer customer;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.name())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.getDeleteDate() == null;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.getAuthenticationAttempts() < MAX_AUTH_ATTEMPTS;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.getLastPasswordChange().isBefore(this.passwordPolicyExpDate);
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public LocalDateTime getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(LocalDateTime deleteDate) {
		this.deleteDate = deleteDate;
	}

	public LocalDateTime getLastPasswordChange() {
		return lastPasswordChange;
	}

	public void setLastPasswordChange(LocalDateTime lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Integer getAuthenticationAttempts() {
		return authenticationAttempts;
	}

	public void setAuthenticationAttempts(Integer authenticationAttempts) {
		this.authenticationAttempts = authenticationAttempts;
	}

	public LocalDateTime getPasswordPolicyExpDate() {
		return passwordPolicyExpDate;
	}

	public void setPasswordPolicyExpDate(LocalDateTime passwordPolicyExpDate) {
		this.passwordPolicyExpDate = passwordPolicyExpDate;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
