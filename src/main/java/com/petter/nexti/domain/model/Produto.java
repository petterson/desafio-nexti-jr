package com.petter.nexti.domain.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.petter.nexti.api.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

////////////ENTYTI PRODUTO UTILIZA BILLIOTECA LOMBOOK PARA ABISTRAIR GERAR GET SET CONSTRUTOR
////////////E UTILIZA BEAN VALIDATION PARA VALIDAR COLUNAS

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {
	
	@NotNull(groups = ValidationGroups.ProdutoId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@Size(max = 80)
	private String nome;
	
	@NotNull
	@NotBlank
	@Size(max = 250)
	private String descricao;
	
	@NotNull
	//@NotBlank
	private BigDecimal preco;
	
	@NotNull
	//@NotBlank
	private int quantidade;

}
