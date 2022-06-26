package com.backend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.backend.service.DetalheUsuarioService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired 
	private DetalheUsuarioService detalheUsuarioService;
	@Autowired 
	private JWTFilter jwtFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario/autenticar").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario/solicitar-cadastro").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario/aceitar-solicitacao").hasAnyAuthority("PESQUISADOR")
		.antMatchers(HttpMethod.GET, "/usuario/listar-solicitacoes").hasAnyAuthority("PESQUISADOR")
		.antMatchers(HttpMethod.GET, "/colaborador/listar-por-campus").hasAnyAuthority("PESQUISADOR")
		.antMatchers(HttpMethod.POST,"/pesquisa/cadastrar-pesquisa").hasAnyAuthority("PESQUISADOR")
		.antMatchers(HttpMethod.GET,"/pesquisa/listar-por-usuario").hasAnyAuthority("PESQUISADOR", "COLABORADOR")
		.anyRequest().authenticated()
		.and().exceptionHandling()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detalheUsuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
}
