package br.com.compra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.compra.domain.PagamentoComBoleto;
import br.com.compra.domain.PagamentoComCartao;

@Configuration
public class JsonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder  objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder= new Jackson2ObjectMapperBuilder() {
			@Override
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				super.configure(objectMapper);
			}
		};
		
		return builder;
	}
}
