package com.ordering.system.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.ordering.system.enums.ClientPerfil;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class UserSpringSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority>  authorities;


    public UserSpringSecurity(Integer id, String email, String password, Set<ClientPerfil> perfis){
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public Integer getId(){
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    
}
