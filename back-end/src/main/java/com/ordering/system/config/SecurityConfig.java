package com.ordering.system.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Environment env;

    private  final String[] PUBLIC_MATCHERS = {
       
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
        .antMatchers(HttpMethod.GET, this.PUBLIC_MATCHERS_GET).permitAll()
        .antMatchers(this.PUBLIC_MATCHERS).permitAll()
        .anyRequest().authenticated();
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
