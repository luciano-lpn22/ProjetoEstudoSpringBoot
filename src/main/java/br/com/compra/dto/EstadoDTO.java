package br.com.compra.dto;

import java.io.Serializable;

import br.com.compra.domain.Estado;

public class EstadoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EstadoDTO() {
	}
	
	public EstadoDTO(Estado estado) {
		this.id=estado.getId();
		this.nome=estado.getNome();
	}
	private int id;
	private String nome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

	
}
