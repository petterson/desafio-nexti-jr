package com.petter.nexti.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petter.nexti.domain.model.Produto;

//////CLASSE PRODUTOREPOSITÓRIO RESPONSÁVEL POR UTILIZAR SERVIÇOS DA INTERFACE JPAREPOSITORY E CRIA NOVOS

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	////////////BUSCA PRODUTO PELO NOME////////////////
	
	Optional<Produto> findByNome(String nome);
}
