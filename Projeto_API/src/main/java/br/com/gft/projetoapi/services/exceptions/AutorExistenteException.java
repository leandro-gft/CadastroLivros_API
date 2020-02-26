package br.com.gft.projetoapi.services.exceptions;

public class AutorExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1302475018570756106L;

	
	public AutorExistenteException(String mensagem) {
		super(mensagem);
	}

	public AutorExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
}
}
