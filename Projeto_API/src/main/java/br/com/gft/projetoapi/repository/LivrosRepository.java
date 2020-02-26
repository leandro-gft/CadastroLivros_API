package br.com.gft.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.projetoapi.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
