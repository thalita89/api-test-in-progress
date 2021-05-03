package com.test.test.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	@OneToOne(mappedBy = "user")
	@JoinColumn(name = "wallet_id", unique = true, nullable = false)
	private Wallet wallet;

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

	public void setId(Long id) {
		this.id = id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
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