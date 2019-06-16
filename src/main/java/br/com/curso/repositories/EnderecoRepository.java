package br.com.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
