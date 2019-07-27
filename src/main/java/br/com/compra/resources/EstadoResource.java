package br.com.compra.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.compra.domain.Cidade;
import br.com.compra.domain.Estado;
import br.com.compra.dto.CidadeDTO;
import br.com.compra.dto.EstadoDTO;
import br.com.compra.services.CidadeService;
import br.com.compra.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> estados= service.findAllByOrderByNome();
		List<EstadoDTO> body= estados.stream().map(obj->new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(body);
	}
	
	@RequestMapping(value = "/{estado_id}/cidades",method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estado_id) {
		List<Cidade> cidades= cidadeService.findCidades(estado_id);
		List<CidadeDTO> dtos=cidades.stream().map(obj->new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
}
