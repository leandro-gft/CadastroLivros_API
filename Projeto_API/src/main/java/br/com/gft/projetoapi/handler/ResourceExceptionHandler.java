package br.com.gft.projetoapi.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.gft.projetoapi.domain.DetalhesErro;
import br.com.gft.projetoapi.services.exceptions.AutorExistenteException;
import br.com.gft.projetoapi.services.exceptions.AutorNaoEncontradoException;
import br.com.gft.projetoapi.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LivroNaoEncontradoException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException(LivroNaoEncontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O livro não pôde ser encontrado!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(AutorExistenteException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleAutorExistenteException(AutorExistenteException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Autor já existente!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.CONFLICT ).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleAutorNaoEncontradoException(AutorNaoEncontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O autor não pôde ser encontrado!");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class) //captura qualquer lugar que chame essa classe 
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida.");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());					
		
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	
	
	
	
	
}
