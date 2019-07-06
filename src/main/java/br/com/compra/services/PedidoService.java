package br.com.compra.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compra.domain.ItemPedido;
import br.com.compra.domain.PagamentoComBoleto;
import br.com.compra.domain.Pedido;
import br.com.compra.domain.enums.EstadoPagamento;
import br.com.compra.repositories.ItemPedidoRepository;
import br.com.compra.repositories.PagamentoRepository;
import br.com.compra.repositories.PedidoRepository;
import br.com.compra.services.exceptions.ObjectNotFoundException;
@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> pedido=repository.findById(id);
		return pedido.orElseThrow(()-> new ObjectNotFoundException("Objeto "+Pedido.class.getName()+" não encontrado ID "+id));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			
			PagamentoComBoleto pagto= (PagamentoComBoleto)obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj=repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido i:obj.getItens()) {
			i.setDesconto(0.0);
			i.setPedido(obj);
			i.setProduto(produtoService.find(i.getProduto().getId()));
			i.setPreco(i.getProduto().getPreco());
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	

}
