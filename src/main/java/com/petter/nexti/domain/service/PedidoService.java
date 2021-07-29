package com.petter.nexti.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petter.nexti.domain.exception.CadastroClienteException;
import com.petter.nexti.domain.model.Pedido;
import com.petter.nexti.domain.repository.PedidoRepository;

import lombok.AllArgsConstructor;

//////////CLASSE PEDIDOSERVICE SERVE PARA FAZER TRANZAÇOẼS COM O BANCO DE DADOS E 

@AllArgsConstructor
@Service
public class PedidoService {

	private ClienteService clienteService;
	private PedidoRepository pedidoRepository;
	private ProdutoService produtoService;

//////////////////LISTA TODOS OS PEDIDOS////////////////////////////////

	@Transactional
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

///////////// BUSCA CLIENTE POR ID/////////////////////////////

	@Transactional
	public Optional<Pedido> findByPedidoPorId(Long pedidoId) {
		return pedidoRepository.findById(pedidoId);
	}

///////////////// SALVA OS PEDIDOS////////////////////////////////////////

	@Transactional
	public Pedido salvar(Pedido pedido) {
			if(!clienteService.existsById(pedido.getCliente().getId())) {
				throw new CadastroClienteException("Não existe cliente com esse Id  " + pedido.getCliente().getId());
			}
			for(int i=0; i<pedido.getProdutos().size(); i++) {
				if(!produtoService.existsById(pedido.getProdutos().get(i).getId())) {
					throw new CadastroClienteException("Não existe Produto com esse Id  " + pedido.getProdutos().get(i).getId());	
				}
			}
			pedido.setDataCompra(OffsetDateTime.now());
		return pedidoRepository.save(pedido);
	}

//////////////////////// ATUALIZA O PEDIDO/////////////////////////

	@Transactional
	public Pedido atualizar(Pedido pedido) {
		if(!clienteService.existsById(pedido.getCliente().getId())) {
			throw new CadastroClienteException("Não existe cliente com esse Id  " + pedido.getCliente().getId());
		}
		for(int i=0; i<pedido.getProdutos().size(); i++) {
			if(!produtoService.existsById(pedido.getProdutos().get(i).getId())) {
				throw new CadastroClienteException("Não existe Produto com esse Id  " + pedido.getProdutos().get(i).getId());	
			}
		}
		pedido.setDataCompra(OffsetDateTime.now());
		return pedidoRepository.save(pedido);
	}

/////////////////// VERIFICA ID PEDIDO/////////////////////////

	@Transactional
	public boolean existsById(Long pedidoId) {
		return pedidoRepository.existsById(pedidoId);
	}

///////////////////////EXCLUI PEDIDO/////////////////////////////////

	@Transactional
	public void excluir(Long pedidoId) {
		if (!this.existsById(pedidoId)) {
			throw new CadastroClienteException("Não Existe Pedido com esse ID " + pedidoId);
		}
		pedidoRepository.deleteById(pedidoId);
	}

}
