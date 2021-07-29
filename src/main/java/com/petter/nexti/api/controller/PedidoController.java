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
import com.petter.nexti.domain.model.Pedido;
import com.petter.nexti.domain.service.PedidoService;

import lombok.AllArgsConstructor;

////////CLASSE PEDIdO CONTROLER DEFINE OS ENDPOINTS DOS PEDIDOS E UTILIZA A CLASSE PEDIDOSERVICE PARA CONECTAR COM DATABASE/////// 

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	////////////////// LISTA TOOS OS PEDIDOS//////////

	@GetMapping
	public List<Pedido> listar() {
		return pedidoService.listar(); 
	}

///////////////// BUSCA PEDIDO POR ID///////////////////////////////////

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable("id") Long id) {
		return pedidoService.findByPedidoPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

//////////////////// ADICIONA UM PEDIDO////////////////////////

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido adicionar(@Valid @RequestBody Pedido pedido) {
		return pedidoService.salvar(pedido);
	}

///////////////////////////ATUALIZA OS PEDIDOS/////////////////////////////

	@PutMapping(path = { "/{id}" })
	public ResponseEntity<Pedido> atualizar(@PathVariable("id") Long id, @Valid @RequestBody Pedido pedido) {
		if (!pedidoService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pedido.setId(id);
		pedido = pedidoService.atualizar(pedido);
		return ResponseEntity.ok(pedido);
	}

///////////////EXCLUI OS PEDIDOS////////////////////////////////////

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		if (!pedidoService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pedidoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
