package com.petter.nexti.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//////@Configuration PERMITE CONFIGURAR BIBLIOTECAS DIFERENTES ISTANCIANDO UM BEAN DA MESMA 

///////////REPRESENTA BIBLIOTECA RESPONSÁVEL POR CONVERTER CLASSES MODELOS
///////////EM FRONT-END, DE FORMA A CONTROLAR A VISIBILIDADE DOS ATRIBUTOS

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapperConfig modelMapper() {
		return new ModelMapperConfig();
	}

}
