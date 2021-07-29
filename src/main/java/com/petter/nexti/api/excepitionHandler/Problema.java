package com.petter.nexti.api.excepitionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


//////////CLASSE CONTÉM ATRIBUTOS UTILIZADOS QUANDO UMA EXCEÇÃO É LANÇADA

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Problema {
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;
	
	@AllArgsConstructor
	@Getter
	public static class Campo{
	     private String nome;
	     private String mensagem;
}

}
