package br.com.compra.resources;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.compra.domain.Pedido;
import br.com.compra.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	PedidoService service;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Pedido> buscar(@PathVariable Integer id){
		Pedido pedido=service.find(id);
		return ResponseEntity.ok(pedido);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Pedido> insert(@Valid @RequestBody Pedido obj) {
		obj=service.insert(obj);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page",defaultValue = "0")  Integer page,
													   @RequestParam(value="linePerPage",defaultValue="24")	Integer linePerPage,
													   @RequestParam(value="orderby" ,defaultValue="instante")	String orderby,
													   @RequestParam(value="direction",defaultValue="DESC")	String direction){
		
		Page<Pedido> pedidoPage =service.findPage(page, linePerPage, orderby, direction);
		return ResponseEntity.ok(pedidoPage);
	}
}
