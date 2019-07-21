package br.com.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.compra.services.S3Service;

@SpringBootApplication
public class CompramcApplication implements  CommandLineRunner{

	@Autowired
	private S3Service service;
	
	public static void main(String[] args) {
		SpringApplication.run(CompramcApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		String localFilePath;
		localFilePath="C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg";
		
		
		service.uploadFile(localFilePath);
		
	}

}
