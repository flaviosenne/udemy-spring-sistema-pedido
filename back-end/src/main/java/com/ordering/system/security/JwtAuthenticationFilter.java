package com.ordering.system.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordering.system.dto.CredentialsDTO;
import com.ordering.system.security.utils.JWTUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JwtAuthenticationFilter( AuthenticationManager authenticationManager,JWTUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

	@Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException{

        try {
            CredentialsDTO credentials;
            credentials = new ObjectMapper().readValue(req.getInputStream(), CredentialsDTO.class);
       
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), new ArrayList<>());

            Authentication auth = this.authenticationManager.authenticate(authToken);
            return auth;
       
        } catch (IOException e) {
            throw new RuntimeException(e);
        } 

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
        String username = ((UserSpringSecurity) auth.getPrincipal()).getUsername();
        String token = this.jwtUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
    }
}
