package br.com.curso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.h2.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.curso.domain.Categoria;
import br.com.curso.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements  CommandLineRunner{

	@Autowired
	CategoriaRepository categoriaRepository;
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1= new Categoria(null,"Escritorio");
		Categoria c2= new Categoria(null,"Informatica");
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		
	}

}
