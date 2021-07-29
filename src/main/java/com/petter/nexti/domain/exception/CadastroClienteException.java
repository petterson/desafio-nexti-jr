package com.petter.nexti.domain.exception;

///////////CLASSE UTILIZADA PARA MANDAR EXCEÇÕES EM TODOS OS CADASTROS

public class CadastroClienteException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CadastroClienteException(String mensagem) {
		super(mensagem);
		
	}

}
