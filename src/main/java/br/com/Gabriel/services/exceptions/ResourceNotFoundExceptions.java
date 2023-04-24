//EXCEPTION PERSONALIZADA PARA ERRO DE USER NOT FOUND
package br.com.Gabriel.services.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundExceptions(Object id) { //VAI PASSAR O ID DO OBJETO QUE TENTO ENCONTRAR (MAS NAO ENCONTROU)
		super("Resource not found. Id " + id);
	}

}
