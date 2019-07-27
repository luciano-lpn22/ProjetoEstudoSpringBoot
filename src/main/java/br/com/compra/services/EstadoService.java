package br.com.compra.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Estado;
import br.com.compra.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	public List<Estado> findAllByOrderByNome() {	
		return repository.findAllByOrderByNome();
	}
	
	
}
