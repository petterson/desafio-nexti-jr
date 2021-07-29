package com.petter.nexti.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petter.nexti.domain.model.Cliente;
import com.petter.nexti.domain.service.ClienteService;

import lombok.AllArgsConstructor;

/////////CLASSE CLIENTE CONTROLER DEFINE OS ENDPOINTS DOS CLIENTES E UTILIZA A CLASSE CLIENTESERVICE PARA CONECTAR COM DATABASE/////// 

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteService cs;

	////////////////// LISTA TOOS OS CLIENTES

	@GetMapping
	public List<Cliente> listar() {
		return cs.listar();
	}

	///////////////// BUSCA CLIENTE POR ID///////////////////////////////////

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable("id") Long id) {
		return cs.findByClientePorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	//////////////////// ADICIONA UM CLIENTE////////////////////////

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		//System.out.println(cliente.getDataNascimento());
		return this.cs.salvar(cliente);
	}

///////////////////////////ATUALIZA OS CLIENTES/////////////////////////////

	@PutMapping(path = { "/{id}" })
	public ResponseEntity<Cliente> atualizar(@PathVariable("id") Long id, @Valid @RequestBody Cliente cliente) {
		if (!cs.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		cliente = cs.atualizar(cliente);
		return ResponseEntity.ok(cliente);
	}

///////////////EXCLUI OS CLIENTES////////////////////////////////////

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		if (!cs.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cs.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
