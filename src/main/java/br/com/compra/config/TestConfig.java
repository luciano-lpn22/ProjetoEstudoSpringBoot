package br.com.compra.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.compra.services.DBService;
import br.com.compra.services.MocMailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		try {
			dbService.instantiateTestDataBase();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		return true;
	}
	
	@Bean
	public MocMailService emailService() {
		return new MocMailService();
	}
	
	
}
