package com.ordering.system.services;

import java.util.Date;

import com.ordering.system.domains.Client;
import com.ordering.system.domains.Requests;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class SendEmailService {
    
    @Autowired
    private MailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${default.sender}")
    private String sender;

    private static final Logger LOG =  LoggerFactory.getLogger(SendEmailService.class);

    public void sendEmail(SimpleMailMessage msg){
        LOG.info("envio de email");
        mailSender.send(msg);
        LOG.info("Email Enviado");
    }

    public void sendOrderConfirmationEmail(Requests requests){

        SimpleMailMessage sm = prepareSimpleMailMesageFromRequests(requests);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMesageFromRequests(Requests requests){
        Context context = new Context();
        context.setVariable("request", requests);

        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(requests.getClient().getEmail());
        sm.setFrom(this.sender);
        sm.setSubject("Pedido Confirmado cod: "+ requests.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(context.toString());
        System.out.println(sm);
        return sm;
    }

	public void sendNewPasswordEmail(Client client, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(client, newPass);
        sendEmail(sm);
	}

    protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(client.getEmail());
        sm.setFrom(this.sender);
        sm.setSubject("Solicitação de nova Senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: "+ newPass);
        
        System.out.println(sm);
        return sm;
    }
}
