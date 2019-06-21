package br.com.compra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compra.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	
}
