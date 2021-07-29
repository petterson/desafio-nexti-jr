package com.petter.nexti.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petter.nexti.domain.model.Cliente;

//////CLASSE CLIENTEREPOSITÓRIO RESPONSÁVEL POR UTILIZAR SERVIÇOS DA INTERFACE JPAREPOSITORY E CRIA NOVOS

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
//////BUSCA CLIENTE POR CPF////////
	
	Optional<Cliente> findByCpf(String cpf);

}
