package br.com.Gabriel.services.exceptions;

public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg); //CHAMA O CONSTRUTOR DA SUPERCLASSE RUNTIME PASSANDO A MENSAGEM (VAI GERAR UMA EXCESSÃO PASSANDO A MENDASGEM QUE MANDAR)
	}

}
