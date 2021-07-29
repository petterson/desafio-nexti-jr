package com.petter.nexti.domain.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.petter.nexti.api.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

////////////ENTYTI CLIENTE UTILIZA BILLIOTECA LOMBOOK PARA ABISTRAIR GERAR GET SET CONSTRUTOR
////////////E UTILIZA BEAN VALIDATION PARA VALIDAR COLUNAS

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {
	
	@NotNull(groups = ValidationGroups.ClienteId.class)
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
	@Size(max = 20)
	private String cpf;
	
	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date dataNascimento;
}
