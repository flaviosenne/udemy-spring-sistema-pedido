package com.ordering.system.services;

import java.util.Date;

import com.ordering.system.domains.Requests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstracEmailService implements EmailService{
    
    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Requests requests){

        SimpleMailMessage sm = prepareSimpleMailMesageFromRequests(requests);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMesageFromRequests(Requests requests){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(requests.getClient().getEmail());
        sm.setFrom(this.sender);
        sm.setSubject("Pedido Confirmado cod: "+ requests.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(requests.toString());
        
        return sm;
    }
}
