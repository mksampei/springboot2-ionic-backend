package com.samarici.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samarici.cursomc.domain.Produto;
import com.samarici.cursomc.dto.ProdutoDTO;
import com.samarici.cursomc.resources.utils.URL;
import com.samarici.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pages")
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(name = "nome", defaultValue = "0") String nome,
			@RequestParam(name = "categorias", defaultValue = "0") String categorias,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {

		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecoded = URL.decodeParam(nome);
		Page<Produto> lista = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);

		Page<ProdutoDTO> listaDto = lista.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listaDto);
	}
}
