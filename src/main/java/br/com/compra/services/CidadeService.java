package br.com.compra.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Cidade;
import br.com.compra.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	public List<Cidade> findCidades(Integer idEstado) {
		return repo.findAllByEstado(idEstado);		
	}
}
