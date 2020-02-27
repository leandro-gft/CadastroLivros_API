package br.com.gft.projetoapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("algaworks").password("{noop}algaworks").roles("USER"); //credenciais para acesso do spring security
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //tipo de proteção para evitar um tipo de ataque em específico
		
			.authorizeRequests().anyRequest().authenticated() //qualquer requisição precisa esta autenticada
			.and()
				.httpBasic();//métood basico de autenticação
			
				
	}
}
