package br.com.compra.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Categoria;
import br.com.compra.repositories.CategoriaRepository;
import br.com.compra.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria= repo.findById(id);
		return categoria.orElseThrow(()->new ObjectNotFoundException("Objeto "+Categoria.class	.getName()+" não encontrado ID: "+id));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}
	

	
}
