package com.petter.nexti.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petter.nexti.domain.exception.CadastroClienteException;
import com.petter.nexti.domain.model.Cliente;
import com.petter.nexti.domain.repository.ClienteRepository;
import com.petter.nexti.domain.repository.PedidoRepository;

import lombok.AllArgsConstructor;

//////////CLASSE CLIENTESERVICE SERVE PARA FAZER TRANZAÇẼS COM O BANCO DE DADOS

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository clienetService;
	private PedidoRepository pedidoRepository;

///////////////////LISTA TODOS OS CLIENTES////////////////////////////////

	@Transactional
	public List<Cliente> listar() {
		return clienetService.findAll();
	}

	///////////// BUSCA CLIENTE POR ID/////////////////////////////

	@Transactional
	public Optional<Cliente> findByClientePorId(Long clienteId) {
		return clienetService.findById(clienteId);
	}

	///////////////// SALVA OS CLIENTES////////////////////////////////////////

	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean cpfEmUso = clienetService.findByCpf(cliente.getCpf()).stream()
				.anyMatch(funcionarioExistente -> !funcionarioExistente.equals(cliente));
		if (cpfEmUso) {
			throw new CadastroClienteException("Já existe Cliente com esse CPF  "+cliente.getCpf());
		}
		return clienetService.save(cliente);
	}

	//////////////////////// ATUALIZA O CLIENTE/////////////////////////

	@Transactional
	public Cliente atualizar(Cliente cliente) {
		return clienetService.save(cliente);
	}

	/////////////////// VERIFICA ID CLIENTE/////////////////////////

	@Transactional
	public boolean existsById(Long clienteId) {
		return clienetService.existsById(clienteId);
	}

     ///////////////////////EXCLUI CLIENTE/////////////////////////////////

	@Transactional
	public void excluir(Long clienteId) {
		if (!this.existsById(clienteId)) {
			throw new CadastroClienteException("Não Existe Cliente com esse ID " + clienteId);
		}
		Long l = pedidoRepository.findByIdDoCliente(clienteId);
		if (l > 0L) {
			throw new CadastroClienteException("Esse cliente tem Restrição de Chave " + clienteId);
		}
		clienetService.deleteById(clienteId);
	}
}
