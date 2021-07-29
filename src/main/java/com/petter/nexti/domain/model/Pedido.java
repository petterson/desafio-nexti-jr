package com.petter.nexti.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.petter.nexti.api.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

////////////ENTYTI PEDIDO UTILIZA BILLIOTECA LOMBOOK PARA ABISTRAIR GERAR GET SET CONSTRUTOR
////////////E UTILIZA BEAN VALIDATION PARA VALIDAR COLUNAS

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
	
	@NotNull(groups = ValidationGroups.PedidoId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@NotNull
	//@NotBlank
	private BigDecimal totalDaCompra;
	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private OffsetDateTime dataCompra;
	
	//@Valid
	//@NotNull
	//@ConvertGroup(from = Default.class, to = ValidationGroups.ProdutoId.class)
	@ManyToMany
	private List<Produto> produtos;

}
