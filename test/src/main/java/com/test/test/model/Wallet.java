package com.test.test.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(precision = 10, scale = 2)
	private BigDecimal valueAccount = BigDecimal.ZERO;

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "user_id", unique = true, nullable = false)
	// @JoinColumn(unique = true, nullable = false)
	// private User user;

	@ManyToMany
	@JoinColumn(name = "user_id", unique = true, nullable = false)
	private List<User> users;

	public Wallet() {
	}

	public Wallet(Long id, BigDecimal valueAccount) {
		this.id = id;
		this.valueAccount = valueAccount;
	}

	// constructor to controller
	public Wallet(BigDecimal valueAccount) {
		this.valueAccount = valueAccount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValueAccount() {
		return valueAccount;
	}

	public void setValueAccount(BigDecimal valueAccount) {
		this.valueAccount = valueAccount;
	}

}
