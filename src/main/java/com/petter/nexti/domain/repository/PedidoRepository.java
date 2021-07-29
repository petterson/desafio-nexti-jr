package com.petter.nexti.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petter.nexti.domain.model.Pedido;

//////CLASSE PEDIDOPOSITÓRIO RESPONSÁVEL POR UTILIZAR SERVIÇOS DA INTERFACE JPAREPOSITORY E CRIA NOVOS

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
////////////VERIFICA SE O ID DO CLIENTE ESTA NA TABELA PEDIDO////////////////
	
	@Query("select c.id from Pedido p inner join p.cliente c where c.id = :clienteId")
    Long findByIdDoCliente(Long clienteId);
	
////////////VERIFICA SE O ID DO PRODUTO ESTA NA TABELA PEDIDO////////////////
	
	@Query("select o.id from Pedido p inner join p.produtos o where o.id = :produtoId")
    List<Long> findByIdDoProdutoList(Long produtoId);
	
}
