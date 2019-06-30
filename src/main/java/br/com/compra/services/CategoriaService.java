package br.com.compra.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Categoria;
import br.com.compra.dto.CategoriaDTO;
import br.com.compra.repositories.CategoriaRepository;
import br.com.compra.services.exceptions.DataIntegrityException;
import br.com.compra.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> categoria= repo.findById(id);
		return categoria.orElseThrow(()->new ObjectNotFoundException("Objeto "+Categoria.class.getName()+" não encontrado ID: "+id));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		Categoria newObj=find(categoria.getId());
		updateData(newObj,categoria);
		return repo.save(categoria);
	}

	private void updateData(Categoria newObj, Categoria categoria) {
		newObj.setNome(categoria.getNome());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Entidade vinculada ha outra Entidade não é possível excluir");
		}
		
		
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderby, String direction){
		PageRequest pageRequest=PageRequest.of(page, linesPerPage,Direction.valueOf(direction) , orderby);
		
		return repo.findAll(pageRequest);
	}

	public Categoria fromDTO(@Valid CategoriaDTO dto) {
		return new Categoria(dto.getId(),dto.getNome());
	}
	

	
}
