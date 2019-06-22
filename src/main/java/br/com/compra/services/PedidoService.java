package br.com.compra.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Pedido;
import br.com.compra.repositories.PedidoRepository;
import br.com.compra.services.exceptions.ObjectNotFoundException;
@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> pedido=repository.findById(id);
		return pedido.orElseThrow(()-> new ObjectNotFoundException("Objeto "+Pedido.class.getName()+" não encontrado ID "+id));
	}
	

}
