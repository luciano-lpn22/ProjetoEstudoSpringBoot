package br.com.compra.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Pagamento;
import br.com.compra.repositories.PagamentoRepository;
import br.com.compra.services.exceptions.ObjectNotFoundException;
@Service
public class PagamentoService {

	@Autowired
	PagamentoRepository repository;
	
	public Pagamento find(Integer id) {
		Optional<Pagamento> pagamento=repository.findById(id);
		return pagamento.orElseThrow(()-> new ObjectNotFoundException("O Objeto "+Pagamento.class.getName()+" id "+id));
	}
	
}
