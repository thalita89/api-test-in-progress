package com.test.test.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userName;
	private String emailAddresses;

	@Column(unique = true)
	private Long cpf;
	private LocalDate dateOfBirth;

	// client by wallet
	// @OneToOne(mappedBy = "user")
	// private Wallet wallet;

	@ManyToMany
	private List<Wallet> wallets;

	// constructor default
	public User() {
	}

	// constructor to POST
	public User(Long id, String userName, String emailAddresses, Long cpf, LocalDate dateOfBirth) {
		this.id = id;
		this.userName = userName;
		this.emailAddresses = emailAddresses;
		this.cpf = cpf;
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmailAddresses() {
		return emailAddresses;
	}

	public Long getCpf() {
		return cpf;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmailAddresses(String emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}