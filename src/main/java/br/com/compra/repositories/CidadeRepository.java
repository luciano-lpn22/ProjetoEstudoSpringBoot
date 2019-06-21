package br.com.compra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compra.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Integer> {

}
