package br.com.compra.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Cliente;
import br.com.compra.repositories.ClienteRepository;
import br.com.compra.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
 
	@Autowired
	ClienteRepository repo;
	public Cliente find(Integer id){
		Optional<Cliente> cliente=repo.findById(id);
		return cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto "+Cliente.class.getName()+" não encontrado ID: "+id));	
	}
}
