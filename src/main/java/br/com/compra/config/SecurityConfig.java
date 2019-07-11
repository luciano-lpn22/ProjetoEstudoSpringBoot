package br.com.compra.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	Environment env;
	
	//liberando endpoint possiveis sem autenticação
	private static final String [] PUBLIC_MATCHES= {
			"/h2-console/**"
	};
	
	//liberando endpoint possiveis sem autenticação
	private static final String [] PUBLIC_MATCHES_GET= {
			"/produtos/**",
			"/categorias/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//liberando o acesso para banco de dados em memoria h2-console
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();			
		}
		
		
		http.cors()//chama o metodo CorsConfigurationSource()
			.and().csrf().disable(); // desabilita recurso de segurança para armazenamento de senha em sessão, pois não utilizamos sessao
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET,PUBLIC_MATCHES_GET).permitAll() //permite acesso sem altenticação
			.antMatchers(PUBLIC_MATCHES).permitAll() //permite acesso sem altenticação
			.anyRequest().authenticated(); //para todas outros endPoint requer autenticação
		//acessura que não vai existir sessão do usuario
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	//permite acesso de qualquer origem
	@Bean
	CorsConfigurationSource CorsConfigurationSource(){
		final UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
