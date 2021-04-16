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
	
	//client by wallet 
	
	@OneToOne
	@JoinColumn(name = "wallet_id", unique = true, nullable = false)
	private Wallet wallet;
	
	//constructor default
	public User() {
	}

	//constructor to POST
	public User(String userName, String emailAddresses, Long cpf, LocalDate dateOfBirth) {
		this.userName = userName;
		this.emailAddresses = emailAddresses;
		this.cpf = cpf;
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	//Refactor later
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	//Refactor later
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmailAddresses() {
		return emailAddresses;
	}


	public void setEmailAddresses(String emailAddresses) {
		this.emailAddresses = emailAddresses;
	}


	public Long getCpf() {
		return cpf;
	}


	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}


	public LocalDate getDateOfBirth() {  
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

//	public Wallet getWallet() {
//		return wallet;
//	}
//
//	public void setWallet(Wallet wallet) {
//		this.wallet = wallet;
//	}

	
}
