// package com.ordering.system.services;

// import java.util.Date;

// import com.ordering.system.domains.Requests;


// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.mail.MailSender;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.stereotype.Component;

// @Component
// public class SendEmailService {
    
//     @Autowired
//     private MailSender mailSender;
    
//     @Value("${default.sender}")
//     private String sender;

//     private static final Logger LOG =  LoggerFactory.getLogger(SendEmailService.class);

//     public void sendEmail(SimpleMailMessage msg){
//         LOG.info("envio de email");
//         mailSender.send(msg);
//         LOG.info("Email Enviado");
//     }

//     public void sendOrderConfirmationEmail(Requests requests){

//         SimpleMailMessage sm = prepareSimpleMailMesageFromRequests(requests);
//         sendEmail(sm);
//     }

//     protected SimpleMailMessage prepareSimpleMailMesageFromRequests(Requests requests){
//         SimpleMailMessage sm = new SimpleMailMessage();
//         sm.setTo(requests.getClient().getEmail());
//         sm.setFrom(this.sender);
//         sm.setSubject("Pedido Confirmado cod: "+ requests.getId());
//         sm.setSentDate(new Date(System.currentTimeMillis()));
//         sm.setText(requests.toString());
        
//         return sm;
//     }
// }
