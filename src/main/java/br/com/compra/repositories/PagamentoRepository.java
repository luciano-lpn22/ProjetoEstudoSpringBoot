package br.com.compra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compra.domain.Pagamento;
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
