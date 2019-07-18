package br.com.compra.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.compra.domain.Cliente;
import br.com.compra.repositories.ClienteRepository;
import br.com.compra.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;

	Random rand= new Random();
	
	public void sendNewPassword(String email) {
		Cliente cliente= clienteRepository.findByEmail(email);
		if(cliente==null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		String 	newPas= newPassWord();
		cliente.setSenha(pe.encode(newPas));
		clienteRepository.save(cliente);
		emailService.sendNewPassWordEmail(cliente,newPas);
		
	}

	private String newPassWord() {
		char [] vet = new char[10];
		for(int i=0;i<10;i++) {
			vet[i]=randow();
		}
		
		return new String(vet);
	}

	private char randow() {
		int opt = rand.nextInt(3);
		
		if (opt==0) {// gera digitos
			return (char) (rand.nextInt(10)+48);
		}else if (opt==1) {// gera letra maiuscula
			return (char) (rand.nextInt(26)+65);
		}else { //gera letras minusculas
			return (char) (rand.nextInt(26)+97);
		}		

	}
}
