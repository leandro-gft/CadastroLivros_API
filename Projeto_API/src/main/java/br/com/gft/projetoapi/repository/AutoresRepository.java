package br.com.gft.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.projetoapi.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

}
