package br.com.gft.projetoapi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.projetoapi.domain.Comentario;
import br.com.gft.projetoapi.domain.Livro;
import br.com.gft.projetoapi.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivroResources {

	@Autowired
	private LivrosService livrosService;

	@GetMapping
	public ResponseEntity<List<Livro>> Listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) { // @RequestBody pega as informações da requisição e
																	// coloca no objeto livro
		livro = livrosService.salvar(livro);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		// para o cliente saber como ele acessa o recurso criado

		return ResponseEntity.created(uri).build(); // cria a resposta da forma correta (201 created, que serve para, ao
													// salvar o recurso, informar o cliente onde ele pode obter mais
													// informações sobre esse recurso)
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) { // ResponseEntity é responsavel por encapsular o
																	// objeto de retorno (no caso livro) e manipular
																	// informações do HTTP. A '?' significa que pode
																	// manipular qualqeur tipo de objeto
		Livro livro =  livrosService.buscar(id); 
		return ResponseEntity.status(HttpStatus.OK).body(livro); // posso setar o status e o corpo da resposta
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		/*
		 * Livro livro = livros.findById(id).orElse(null); if(livro == null) { return
		 * ResponseEntity.notFound().build(); //retorna um 404, senão retorna um 200 OK
		 * e o corpo da mensagem } livros.deleteById(id); return
		 * ResponseEntity.noContent().build(); }
		 */
		
		livrosService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosService.atualizar(livro);
		return ResponseEntity.noContent().build();
	}

	// http://tools.ietf.org/html/rfc7231 : documentação para consultar como tratar
	// respostas adequadamente
	
	
	
	@PostMapping("/{id}/comentarios")
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, @RequestBody Comentario comentario) {
		
		livrosService.salvarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
		
			
	}
	@GetMapping("/{id}/comentarios")
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId) {
		List<Comentario> comentarios = livrosService.listarComentarios(livroId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
	
		
}
