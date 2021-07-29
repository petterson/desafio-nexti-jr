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
import com.petter.nexti.domain.model.Produto;
import com.petter.nexti.domain.service.ProdutoService;

import lombok.AllArgsConstructor;

/////////CLASSE PRODUTO CONTROLER DEFINE OS ENDPOINTS DOS PRODUTOS E UTILIZA A CLASSE PRODUTOSERVICE PARA CONECTAR COM DATABASE/////// 

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoService produtoService;

	////////////////// LISTA TODOS OS PRODUTOS

	@GetMapping
	public List<Produto> listar() {
		return produtoService.listar();
	}

///////////////// BUSCA PRODUTO POR ID///////////////////////////////////

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable("id") Long id) {
		return produtoService.findByProdutoPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

////////////////////ADICIONA UM PRODUTO////////////////////////

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@Valid @RequestBody Produto produto) {
		return produtoService.salvar(produto);
	}

///////////////////////////ATUALIZA OS PRODUTOS/////////////////////////////

	@PutMapping(path = { "/{id}" })
	public ResponseEntity<Produto> atualizar(@PathVariable("id") Long id, @Valid @RequestBody Produto produto) {
		if (!produtoService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		produto.setId(id);
		produto = produtoService.atualizar(produto);
		return ResponseEntity.ok(produto);
	}

//////////////EXCLUI OS PRODUTOS////////////////////////////////////

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		if (!produtoService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		produtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
