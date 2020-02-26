package br.com.gft.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.projetoapi.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {

}
