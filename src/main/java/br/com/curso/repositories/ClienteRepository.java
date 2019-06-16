package br.com.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
