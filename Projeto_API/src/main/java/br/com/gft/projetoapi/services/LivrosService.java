package br.com.gft.projetoapi.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.projetoapi.domain.Comentario;
import br.com.gft.projetoapi.domain.Livro;
import br.com.gft.projetoapi.repository.ComentariosRepository;
import br.com.gft.projetoapi.repository.LivrosRepository;
import br.com.gft.projetoapi.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livros;

	@Autowired
	private ComentariosRepository comentariosRepository;

	public List<Livro> listar() {
		return livros.findAll();
	}

	public Livro buscar(Long id) {
		Livro livro = livros.findById(id).orElse(null);

		if (livro == null) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");

		}

		return livro;
	}

	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livros.save(livro);
	}

	public void deletar(Long id) {

		try {
			livros.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
	}

	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livros.save(livro);
	}

	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}

	public Comentario salvarComentario(Long livroId, Comentario comentario) {
		Livro livro = buscar(livroId);
		comentario.setLivro(livro);
		comentario.setData(new Date());

		return comentariosRepository.save(comentario);
	}

	public List<Comentario> listarComentarios(Long livroId) {
		Livro livro = buscar(livroId);
		return livro.getComentarios();
	}
}
