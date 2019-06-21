package br.com.compra.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import br.com.compra.domain.enums.EstadoPagamento;
@Entity
public class PagamentoComCartao extends Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public PagamentoComCartao() {}
	public PagamentoComCartao(Pedido pedido, EstadoPagamento estado,Integer nummeroDeParcelas) {
		super(pedido, estado);
		this.nummeroDeParcelas=nummeroDeParcelas;
	}

	private Integer nummeroDeParcelas;
	
	public Integer getNummeroDeParcelas() {
		return nummeroDeParcelas;
	}

	public void setNummeroDeParcelas(Integer nummeroDeParcelas) {
		this.nummeroDeParcelas = nummeroDeParcelas;
	}
	

}
