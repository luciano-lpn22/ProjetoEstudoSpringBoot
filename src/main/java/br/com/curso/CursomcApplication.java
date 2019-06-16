package br.com.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.curso.domain.Categoria;
import br.com.curso.domain.Produto;
import br.com.curso.repositories.CategoriaRepository;
import br.com.curso.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements  CommandLineRunner{

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1= new Categoria(null,"Informatica");
		Categoria c2= new Categoria(null,"Escritorio");
		
		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);
		
		c2.getProdutos().addAll(Arrays.asList(p2));
		c1.getProdutos().addAll(Arrays.asList(p1,p3,p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}

}
