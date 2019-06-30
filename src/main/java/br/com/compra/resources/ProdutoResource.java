package br.com.compra.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compra.domain.Produto;
import br.com.compra.dto.ProdutoDTO;
import br.com.compra.resources.utils.URL;
import br.com.compra.services.ProdutoService;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	@RequestMapping(value = "/id",method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto produto= service.find(id);
		return ResponseEntity.ok(produto);	
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> search(	@RequestParam(name = "nome" ,defaultValue = "") String nome,
													@RequestParam(name = "categorias" ,defaultValue = "") String categorias,
													@RequestParam(name = "page",defaultValue = "0") Integer page,
													@RequestParam(name="liePerPage",defaultValue = "24") Integer linePerPage,
													@RequestParam(name="orderBy",defaultValue = "nome") String orderby,
													@RequestParam(name="direction",defaultValue ="ASC")String direction){
		List<Integer> ids=URL.decodeToList(categorias);
		String nomeDecode=URL.decodeParam(nome);
		Page<Produto> produtosPage= service.search(nomeDecode, ids, page, linePerPage, orderby, direction);
		Page<ProdutoDTO> listDto=produtosPage.map(obj->new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
