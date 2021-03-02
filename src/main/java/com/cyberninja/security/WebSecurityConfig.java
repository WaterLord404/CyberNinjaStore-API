package com.cyberninja.security;


import static com.cyberninja.security.common.SecurityConstants.LOG_IN;
import static com.cyberninja.security.common.SecurityConstants.SIGN_UP_URL;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.cyberninja.security.filter.JWTAuthenticationFilter;
import com.cyberninja.security.filter.JWTAuthorizationFilter;
import com.cyberninja.security.model.entity.enun.UserRole;
import com.cyberninja.security.services.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;

	@Autowired
	private UserServiceImpl userService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(POST, SIGN_UP_URL).permitAll()
			.antMatchers(POST, LOG_IN).permitAll()
			.antMatchers(GET, "/product").permitAll()
			.antMatchers(GET, "/product/*").permitAll()
			.antMatchers(POST, "/product").hasRole(UserRole.ADMIN.name())
			.antMatchers(DELETE, "/product").hasRole(UserRole.ADMIN.name())
		.anyRequest()
		.authenticated()	
		.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManagerBean(), userService))
			.addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class).sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEnconder());
	}

	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}

}
