package com.samarici.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samarici.cursomc.domain.Cliente;
import com.samarici.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
