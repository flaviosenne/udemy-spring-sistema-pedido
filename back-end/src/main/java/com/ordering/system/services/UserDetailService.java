package com.ordering.system.services;

import com.ordering.system.domains.Client;
import com.ordering.system.repositories.ClientRepository;
import com.ordering.system.security.UserSpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       
        Client client = this.clientRepository.findByEmail(email);

        if(client == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSpringSecurity(client.getId(), client.getEmail(), client.getPassword(), client.getPerfis());
    }
    
}
