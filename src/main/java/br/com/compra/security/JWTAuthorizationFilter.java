package br.com.compra.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter  extends BasicAuthenticationFilter{

	private JWTUtil jwtUtil;
	private UserDetailsService detailsService;
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,JWTUtil jwtUtil,UserDetailsService detailsService) {
		super(authenticationManager);
		this.jwtUtil=jwtUtil;
		this.detailsService=detailsService;
	}
	
	//executa antes de deixa a requisição continuar 	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
		String header=request.getHeader("Authorization");
		if (header!=null && header.startsWith("Bearer")) {
			UsernamePasswordAuthenticationToken auth=getAuthoration(header.substring(7));
			if (auth !=null) {
				//libera o acesso o token é valido
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthoration(String token) {
		if (jwtUtil.tokenValido(token)) {
			String userName=jwtUtil.getUserName(token);
			UserDetails user=detailsService.loadUserByUsername(userName);
			return  new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		}
		return null;
	}

}
