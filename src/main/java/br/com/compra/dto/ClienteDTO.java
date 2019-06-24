package br.com.compra.dto;

import java.io.Serializable;

import br.com.compra.domain.Cliente;

public class ClienteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente cliente) {
		this.id=cliente.getId();
		this.nome=cliente.getNome();
		this.email=cliente.getEmail();
	}
	
	private Integer id;
	private String nome;
	private String email;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
}
