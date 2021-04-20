package com.test.test.model;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//mesma logica de:
	//private LocalDateTime dataCriacao = LocalDateTime.now();
	//pesquisei na documentacao
	//@Column(precision = 10, scale = 2) ?
	private BigDecimal valueAccount = BigDecimal.ZERO;
	
	//quero receber os dados do meu usuario buscando pelo id
	//POST - wallet puxa do corpo da requisição com dados de cliente e saldo conta
	//PUT - USER permite alterar usuario e add conta

	@OneToOne(mappedBy = "wallet")
	@JoinColumn(unique = true)
	private User user;

	public Wallet() {
	}

	public Wallet(Long id, BigDecimal valueAccount, User user) {
		this.id = id;
		this.valueAccount = valueAccount;
		this.user = user;
	}

	public Wallet(Long id, BigDecimal valueAccount, Optional<User> user) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
