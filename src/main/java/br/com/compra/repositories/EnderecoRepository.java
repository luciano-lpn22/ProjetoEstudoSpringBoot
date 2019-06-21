package br.com.compra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compra.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
