package com.petter.nexti.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petter.nexti.domain.exception.CadastroClienteException;
import com.petter.nexti.domain.model.Produto;
import com.petter.nexti.domain.repository.PedidoRepository;
import com.petter.nexti.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

//////////////CLASSE CONECTA COM BASE DE DADOS//////////////////

@AllArgsConstructor
@Service
public class ProdutoService {

	private ProdutoRepository produtoReposutory;
	private PedidoRepository pedidoRepository;

///////////////////LISTA TODOS OS PRODUTOS////////////////////////////////

	@Transactional
	public List<Produto> listar() {
		return produtoReposutory.findAll();
	}

///////////// BUSCA PRODUTO POR ID/////////////////////////////

	@Transactional
	public Optional<Produto> findByProdutoPorId(Long produtoId) {
		return produtoReposutory.findById(produtoId);
	}

///////////////// SALVA OS PRODUTOS////////////////////////////////////////

	@Transactional
	public Produto salvar(Produto produto) {
		boolean nomeEmUso = produtoReposutory.findByNome(produto.getNome()).stream()
				.anyMatch(funcionarioExistente -> !funcionarioExistente.equals(produto));
		if (nomeEmUso) {
			throw new CadastroClienteException("Já existe Produto com esse Nome  " + produto.getNome());
		}
		return produtoReposutory.save(produto);
	}

////////////////////////ATUALIZA O produto/////////////////////////

	@Transactional
	public Produto atualizar(Produto produto) {
		return produtoReposutory.save(produto);
	}

/////////////////// VERIFICA ID PRODUTO/////////////////////////

	@Transactional
	public boolean existsById(Long produtoId) {
		return produtoReposutory.existsById(produtoId);
	}

///////////////////////EXCLUI PRODUTO/////////////////////////////////

	@Transactional
	public void excluir(Long produtoId) {
		if (!this.existsById(produtoId)) {
			throw new CadastroClienteException("Não Existe Produto com esse ID " + produtoId);
		}
		List<Long> l = pedidoRepository.findByIdDoProdutoList(produtoId);
		if (l.size() >= 1) {
			throw new CadastroClienteException("Esse Produto tem Restrição de Chave " + produtoId);
		}
		produtoReposutory.deleteById(produtoId);
	}

}
