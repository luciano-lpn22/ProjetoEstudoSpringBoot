package br.com.compra;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompramcApplication implements  CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CompramcApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {}

}
