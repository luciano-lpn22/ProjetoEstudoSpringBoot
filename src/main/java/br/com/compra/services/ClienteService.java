package br.com.compra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Cliente;
import br.com.compra.dto.ClienteDTO;
import br.com.compra.repositories.ClienteRepository;
import br.com.compra.services.exceptions.DataIntegrityException;
import br.com.compra.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
 
	@Autowired
	ClienteRepository repo;
	public Cliente find(Integer id){
		Optional<Cliente> cliente=repo.findById(id);
		return cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto "+Cliente.class.getName()+" não encontrado ID: "+id));	
	}
	public Cliente fromDTO(Cliente newObj, ClienteDTO dto) {
		newObj.setNome(dto.getNome());
		newObj.setEmail(dto.getEmail());
		return newObj;
	}
	public void update(Cliente cliente) {
		find(cliente.getId());
		repo.save(cliente);		
	}
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Entidade vinculada ha outra Entidade não é possível excluir");
		}
		
	}
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	public Page<Cliente> findPage(Integer pagina, Integer linePerPage, String orderby, String direction) {
		PageRequest page=PageRequest.of(pagina, linePerPage, Direction.valueOf(direction), orderby);
		return repo.findAll(page);
	}
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getNome(),dto.getEmail());
	}
	public Cliente insert(Cliente cliente) {
		return repo.save(cliente);
	}
}
