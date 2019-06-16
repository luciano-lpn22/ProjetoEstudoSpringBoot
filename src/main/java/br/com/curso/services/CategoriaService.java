package br.com.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.domain.Categoria;
import br.com.curso.repositories.CategoriaRepository;
import br.com.curso.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria= repo.findById(id);
		return categoria.orElseThrow(()->new ObjectNotFoundException("Objeto "+Categoria.class	.getName()+" não encontrado ID: "+id));
	}
	

}
