package br.com.compra.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.compra.domain.Categoria;

public class CategoriaDTO implements Serializable {

	
	/**
	 * 
	 */
	
	public CategoriaDTO(){}
	public CategoriaDTO(Categoria categoria){
		this.id=categoria.getId();
		this.nome=categoria.getNome();
		
	}
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message = "Preencimento Obrigatório")
	@Length(max = 80,min =5 ,message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
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
	
	
}
