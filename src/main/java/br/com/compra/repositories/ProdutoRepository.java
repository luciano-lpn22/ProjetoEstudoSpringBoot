package br.com.compra.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compra.domain.Categoria;
import br.com.compra.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

	/*@Query("	SELECT DISTINCT obj "
			+ "	FROM Produto obj "
			+ "	INNER JOIN obj.categorias cat "
			+ " WHERE obj.nome like %:nome% "
			+ " AND  cat IN :categorias")
	public Page<Produto> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	*/
	//pode ser utilizar este medodo abaixo em substitição da query acima, onde o ByNome corresponde ao primerio parametro e categorias ao segundo parametros
	public Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,List<Categoria> categorias, Pageable pageRequest);	
		
	

}
