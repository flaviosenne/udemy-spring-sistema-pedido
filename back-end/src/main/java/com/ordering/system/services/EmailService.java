package com.ordering.system.services;

import com.ordering.system.domains.Requests;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    
    void sendOrderConfirmationEmail(Requests requests);

    void sendEmail(SimpleMailMessage msg);
}
