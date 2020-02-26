package br.com.gft.projetoapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.projetoapi.domain.Autor;
import br.com.gft.projetoapi.repository.AutoresRepository;
import br.com.gft.projetoapi.services.exceptions.AutorExistenteException;
import br.com.gft.projetoapi.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {
	
	@Autowired
	private AutoresRepository autoresRepository;
	
	
	public List<Autor> listar() {
		return autoresRepository.findAll();

	}
	
	public Autor salvar(Autor autor) {
		if (autor.getId() !=null) {
			Autor a  = autoresRepository.findById(autor.getId()).orElse(null);
			
			if (a != null) //se a for diferente de null, significa que foi encontrado no banco, ou seja, já existe
				{
				throw new AutorExistenteException("O autor já existe.");				
			}			
		}
		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id) {
		Autor autor = autoresRepository.findById(id).orElse(null);
		
		if(autor ==null) {
			throw new AutorNaoEncontradoException("O autor não pôde ser encontrado.");
			
		}
		return autor;
		
		
	}

}
