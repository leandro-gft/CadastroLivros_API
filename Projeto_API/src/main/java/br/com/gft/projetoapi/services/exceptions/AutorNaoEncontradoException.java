package br.com.gft.projetoapi.services.exceptions;

public class AutorNaoEncontradoException extends RuntimeException { /**
	 * 
	 */
	private static final long serialVersionUID = 8835652137717098582L;

//exceção Runtime é uma exceção não checada, isto é, se o livro não for encontrado  poderemos tomar alguma ação em nivel de código, então lançaremos para camadas superiores para o tratamento adequado.
	
public AutorNaoEncontradoException(String mensagem) {
	super(mensagem);
}

public AutorNaoEncontradoException(String mensagem, Throwable causa) {
	super(mensagem, causa);
}
}
