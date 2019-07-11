package br.com.compra.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.compra.services.validation.ClienteInsert;
@ClienteInsert
public class ClienteNewDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(max = 120,min = 5, message = "O tamanho deveser maior que 5 e menor que 120")
	private String nome;
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Email
	private String email;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String cpfOuCnpj;
	
	private String senha;
	
	private Integer tipo;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String logradouro;
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String numero;
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String complemento;
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String bairro;
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String cep;
	@NotEmpty(message = "Preenchimento Obrigatório")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;

	private Integer cidadeId;
	
	public ClienteNewDTO() {}
	
	
	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getTelefone3() {
		return telefone3;
	}
	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	public Integer getCidadeId() {
		return cidadeId;
	}
	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
}
