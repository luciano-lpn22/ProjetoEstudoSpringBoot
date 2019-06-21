package br.com.compra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compra.domain.Pedido;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
