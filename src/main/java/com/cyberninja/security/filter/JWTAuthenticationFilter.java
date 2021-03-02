package com.cyberninja.security.filter;

import static com.cyberninja.security.common.SecurityConstants.HEADER_STRING;
import static com.cyberninja.security.common.SecurityConstants.LOG_IN;
import static com.cyberninja.security.common.SecurityConstants.TOKEN_PREFIX;
import static com.cyberninja.security.filter.jwt.JWTTokenProvider.generateToken;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cyberninja.security.model.entity.User;
import com.cyberninja.security.model.entity.dto.UserDTO;
import com.cyberninja.security.services.UserServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebFilter
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UserServiceI userService;
	
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserServiceI userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        setFilterProcessesUrl(LOG_IN);
    }
	
	private UserDTO user;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		user = null;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return 	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), 
																						   user.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + generateToken(((User)authResult.getPrincipal())));

        ObjectMapper objectMapper = new ObjectMapper();
        String userDTO = objectMapper.writeValueAsString(userService.getUser(user));

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userDTO);
        out.flush();
	}

}
