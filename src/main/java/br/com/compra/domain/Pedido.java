package br.com.compra.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Pedido() {
		
	}
	


	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instante;
	
	
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido")
	private Pagamento pagamento;
	
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private Endereco enderecoEntrega;
	
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens= new HashSet<ItemPedido>();
	
	
	public Double getValorTottal() {
		Double soma =0.0;
		for(ItemPedido ip: itens) {
			soma=soma+ip.getSubTotal();
		}
		return soma;
	}
	public Set<ItemPedido> getItens() {
		return itens;
	}



	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}



	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getInstante() {
		return instante;
	}
	public void setInstante(Date instante) {
		this.instante = instante;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		NumberFormat n= NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append(" Pedido Número: ");
		builder.append(getId());
		builder.append(", Instante ");
		builder.append(f.format(getInstante()));
		builder.append(", Cliente ");
		builder.append(getCliente().getNome());
		builder.append(", Situação do Pagamento ");
		builder.append(getPagamento().getEstado().getDescricao());
		builder.append("\n Detalhes: \n");
		for (ItemPedido i:getItens()) {
			builder.append(i.toString());
		}
		builder.append("Valor  Total: ");
		builder.append(n.format(getValorTottal()));
		return builder.toString();
	}
	
}
