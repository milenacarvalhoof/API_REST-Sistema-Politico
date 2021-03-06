package com.gft.sistema_politico.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gft.sistema_politico.filter.FiltroAutenticacao;
import com.gft.sistema_politico.services.AutenticacaoService;
import com.gft.sistema_politico.services.UsuarioService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final UsuarioService usuarioService;
	private final AutenticacaoService autenticacaoService;
	
	public SecurityConfiguration(UsuarioService service, @Lazy AutenticacaoService autenticacaoService) {
		this.usuarioService = service;
		this.autenticacaoService = autenticacaoService;
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/v1/auth").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/vereadores").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/vereadores/{id}").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/senadores").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/senadores/{id}").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/presidentes").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/presidentes/{id}").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/prefeitos").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/prefeitos/{id}").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/deputados").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/deputados/{id}").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/governadores").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/governadores/{id}").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/ministros").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/ministros/{id}").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/partidos").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/partidos/{id}").permitAll()
			.anyRequest()
			.authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new FiltroAutenticacao(autenticacaoService, usuarioService), UsernamePasswordAuthenticationFilter.class); 
	}

	
	
}
