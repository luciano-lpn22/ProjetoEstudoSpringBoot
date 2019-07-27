package br.com.compra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.compra.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

	@Transactional(readOnly = true)
	@Query(" select a from Cidade a where a.estado.id=:estadoId order by a.nome ")
	public List<Cidade> findAllByEstado(@Param("estadoId") Integer idEstado);

}
