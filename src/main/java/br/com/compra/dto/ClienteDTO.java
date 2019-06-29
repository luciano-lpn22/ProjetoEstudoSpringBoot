package br.com.compra.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(max = 120,min = 5, message = "O tamanho deveser maior que 5 e menor que 120")
	private String nome;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Email(message = "E-mail inválido")
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
