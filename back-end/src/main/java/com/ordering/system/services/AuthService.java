package com.ordering.system.services;

import java.util.Optional;
import java.util.Random;

import com.ordering.system.domains.Client;
import com.ordering.system.exceptions.ObjectNotFoundException;
import com.ordering.system.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private SendEmailService emailService;


    private Random rand = new Random();

    public void sendNewPassword(String email){

        Optional<Client> client = this.clientRepository.findByEmail(email);

        if(!client.isPresent()){
            throw new ObjectNotFoundException("Email not found");
        }

        String newPass = newPassword();
        client.get().setPassword(bcrypt.encode(newPass));

        this.clientRepository.save(client.get());

        this.emailService.sendNewPasswordEmail(client.get(), newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for(int i=0; i<10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if(opt == 0){ // digit
            return (char) (rand.nextInt(10) + 48);

        }else if(opt == 1){ // uperCase
            return (char) (rand.nextInt(26) + 65);
             
        }else{ // lowerCase
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
