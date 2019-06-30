package br.com.compra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Categoria;
import br.com.compra.domain.Produto;
import br.com.compra.repositories.CategoriaRepository;
import br.com.compra.repositories.ProdutoRepository;
import br.com.compra.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository repoCategoria;
	
	public Produto find(Integer id) {
		
		Optional<Produto> obj = repo.findById(id);
		return	obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado ID "+id+", Tipo: "+Produto.class.getName()));
	}
	
	public Page<Produto> find(Integer page,Integer linePerPage,String orderBy,String direction ){
		PageRequest request=PageRequest.of(page, linePerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(request);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page,Integer linePerPage,String orderBy,String direction){
		PageRequest request=PageRequest.of(page, linePerPage, Direction.valueOf(direction),orderBy );
		List<Categoria> categorias=repoCategoria.findAllById(ids); 
		return repo.search(nome,categorias,request);
	}
}
