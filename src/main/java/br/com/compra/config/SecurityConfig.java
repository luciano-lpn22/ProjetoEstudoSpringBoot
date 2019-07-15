package br.com.compra.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.compra.security.JWTAuthenticationFilter;
import br.com.compra.security.JWTAuthorizationFilter;
import br.com.compra.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //possibilita a configuração de metodo com conexao ao edpoint pre-estabelecida,edpoint por perfil de acesso
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	Environment env;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	//liberando endpoint possiveis sem autenticação
	private static final String [] PUBLIC_MATCHES= {
			"/h2-console/**"
	};
	
	//liberando endpoint possiveis sem autenticação
	private static final String [] PUBLIC_MATCHES_GET= {
			"/produtos/**",
			"/categorias/**",
			"/clientes/**",
			};
	
	//liberando endpoint possiveis sem autenticação
		private static final String [] PUBLIC_MATCHES_POST= {
				"/clientes/**"
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
			.antMatchers(HttpMethod.POST,PUBLIC_MATCHES_POST).permitAll()//permite acesso ao endpoint POST
			.antMatchers(HttpMethod.GET,PUBLIC_MATCHES_GET).permitAll() //permite acesso sem altenticação
			.antMatchers(PUBLIC_MATCHES).permitAll() //permite acesso sem altenticação
			.anyRequest().authenticated(); //para todas outros endPoint requer autenticação

		//adiciona a verificação de autenticação do token
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		//acessura que não vai existir sessão do usuario em cache
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	//verifica o usuario e o tipo de criptografia
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
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
