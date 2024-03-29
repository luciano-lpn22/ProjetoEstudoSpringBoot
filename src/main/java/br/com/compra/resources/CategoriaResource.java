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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.compra.domain.Categoria;
import br.com.compra.dto.CategoriaDTO;
import br.com.compra.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	CategoriaService service;
	@RequestMapping( value = "/{id}",method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria categoria=  service.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //SOMENTE O ADMIN PODERA CONSUMIR ESTE METODO
	@RequestMapping(method =RequestMethod.POST)
	public ResponseEntity<Void> insert (@Valid @RequestBody CategoriaDTO dto){
		Categoria categoria= service.fromDTO(dto);
		
		categoria=service.insert(categoria);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest()
											.path("/{id}")
											.buildAndExpand(categoria.getId())
											.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //SOMENTE O ADMIN PODERA CONSUMIR ESTE METODO
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update (@RequestBody CategoriaDTO dto,@PathVariable Integer id){
		Categoria categoria= service.fromDTO(dto);
		categoria.setId(id);
		categoria=service.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //SOMENTE O ADMIN PODERA CONSUMIR ESTE METODO
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> fidAll(){
		List<Categoria> categorias =service.findAll();
		List<CategoriaDTO> dtos = categorias.stream().map(obj-> new CategoriaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtos);
	}
	
	@RequestMapping(value="/paginacao",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page",defaultValue = "0")  Integer page,
													   @RequestParam(value="linePerPage",defaultValue="24")	Integer linePerPage,
													   @RequestParam(value="orderby" ,defaultValue="nome")	String orderby,
													   @RequestParam(value="direction",defaultValue="ASC")	String direction){
		
		Page<Categoria>pageCategoria=service.findPage(page, linePerPage, orderby, direction);
		Page<CategoriaDTO> pageDto=pageCategoria.map(obj->new CategoriaDTO(obj));
		return ResponseEntity.ok(pageDto);
	}
	
	
	
}
