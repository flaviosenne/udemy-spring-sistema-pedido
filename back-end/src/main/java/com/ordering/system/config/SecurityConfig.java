package com.ordering.system.config;

import java.util.Arrays;

import com.ordering.system.security.JWTAuthorizationFilter;
import com.ordering.system.security.JwtAuthenticationFilter;
import com.ordering.system.security.utils.JWTUtil;
import com.ordering.system.services.UserDetailService;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Environment env;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailService userDetailsService;

    private  final String[] PUBLIC_MATCHERS = {
       
    };

    private  final String[] PUBLIC_MATCHERS_POST = {
        "/clients/**",
        "/auth/forgot/**",
            "/auth/login/**"
    };
    private  final String[] PUBLIC_MATCHERS_GET = {
        "/products/**",
        "/categories/**"
    };
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable();
        http.authorizeRequests()
        .antMatchers(HttpMethod.POST, this.PUBLIC_MATCHERS_POST).permitAll()
        .antMatchers(HttpMethod.GET, this.PUBLIC_MATCHERS_GET).permitAll()
        .antMatchers(this.PUBLIC_MATCHERS).permitAll()
        .anyRequest().authenticated();

        http.addFilter(new JwtAuthenticationFilter(authenticationManager(), this.jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), this.jwtUtil, this.userDetailsService));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.bCryptPasswordEncoder());
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean 
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
