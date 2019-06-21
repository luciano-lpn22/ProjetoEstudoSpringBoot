package br.com.compra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compra.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
