package com.orange.talents.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orange.talents.domain.ContaBanco;

public class ContaBancoDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	@NotEmpty(message = "Campo Obrigatório")
	private String nome;
	
	@Email
	@NotEmpty(message = "Campo Obrigatório")
	private String email;
	
	@NotEmpty(message = "Campo Obrigatório")
	@CPF
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Campo Obrigatório")
	private Date dataNasc;

	public ContaBancoDTO(ContaBanco conta) {
		nome = conta.getNome();
		email = conta.getEmail();
		cpf = conta.getCpf();
		dataNasc = conta.getDataNasc();
	}

	public ContaBancoDTO() {
		super();
	}

	public ContaBancoDTO(String nome, String email, String cpf, Date dataNasc) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

}
