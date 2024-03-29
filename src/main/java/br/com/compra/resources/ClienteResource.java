package br.com.compra.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.compra.domain.Cliente;
import br.com.compra.dto.ClienteDTO;
import br.com.compra.dto.ClienteNewDTO;
import br.com.compra.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	ClienteService service;
	
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		Cliente cliente= service.find(id);
		return  ResponseEntity.ok(cliente);
	} 
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO dto,@PathVariable Integer id){
		Cliente newObj= service.find(id);
		Cliente cliente= service.fromDTO(newObj,dto);
		service.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> clientes=service.findAll();
		List<ClienteDTO> dtos= clientes.stream().map(obj-> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/paginacao",method =RequestMethod.GET )
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(name ="page",defaultValue = "0" ) Integer page,
													 @RequestParam(name="linePerPage",defaultValue="24") Integer linePerPage,
													 @RequestParam(name="orderby",defaultValue="nome") String orderby,
													 @RequestParam(name="direction",defaultValue="ASC") String direction){
		Page<Cliente> clientes= service.findPage(page,linePerPage,orderby,direction);
		Page<ClienteDTO> dtos=clientes.map(obj-> new ClienteDTO(obj));
		return ResponseEntity.ok(dtos);  
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO dto){
		Cliente cliente = service.fromDTO(dto);
		cliente=service.insert(cliente);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest()
											.path("/{id}")
											.buildAndExpand(cliente.getId())
											.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value = "/picture",method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePictory(@RequestParam(name = "file") MultipartFile multPart){
		URI uri=service.uploadProfilePicture(multPart);
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/email",method=RequestMethod.GET)
	public ResponseEntity<Cliente> findEmail(@RequestParam(value = "value")String email){
		Cliente cli=service.findByEmail(email);	
		return ResponseEntity.ok(cli);
	}
	
}
