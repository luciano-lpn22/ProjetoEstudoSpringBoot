package br.com.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Integer> {

}
