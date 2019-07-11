package br.com.compra.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compra.domain.Cidade;
import br.com.compra.domain.Cliente;
import br.com.compra.domain.Endereco;
import br.com.compra.domain.enums.TipoCliente;
import br.com.compra.dto.ClienteDTO;
import br.com.compra.dto.ClienteNewDTO;
import br.com.compra.repositories.ClienteRepository;
import br.com.compra.repositories.EnderecoRepository;
import br.com.compra.services.exceptions.DataIntegrityException;
import br.com.compra.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
 
	@Autowired
	ClienteRepository repo;
	@Autowired
	EnderecoRepository enderecoRepository;
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
		Cliente newObj=find(cliente.getId());
		updateData(newObj,cliente);
		repo.save(newObj);		
	}
	private void updateData(Cliente newObj, Cliente cliente) {
			newObj.setNome(cliente.getNome());
			newObj.setEmail(cliente.getEmail());
			
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
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente= new Cliente(	dto.getNome(),
							dto.getEmail(),
							dto.getCpfOuCnpj(),
							TipoCliente.toEnum(dto.getTipo()),pe.encode(dto.getSenha()));
		
		Cidade cidade=new Cidade(dto.getCidadeId());
		Endereco endereco= new Endereco(dto.getLogradouro(),
										dto.getNumero(),
										dto.getComplemento(),
										dto.getBairro(),
										dto.getCep(),
										cliente,
										cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2()!=null) {
			cliente.getTelefones().add(dto.getTelefone2());	
		}
		if (dto.getTelefone3()!=null) {
			cliente.getTelefones().add(dto.getTelefone3());	
		}
		
		return cliente;
	}
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente= repo.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
}
