package com.ordering.system.services;

import com.ordering.system.domains.Client;
import com.ordering.system.domains.PaymentTicket;
import com.ordering.system.domains.RequestItem;
import com.ordering.system.domains.Requests;
import com.ordering.system.enums.PaymentStatus;
import com.ordering.system.exceptions.AuthorizationException;
import com.ordering.system.repositories.ClientRepository;
import com.ordering.system.repositories.PaymentRepository;
import com.ordering.system.repositories.RequestItemRepository;
import com.ordering.system.repositories.RequestsRepository;
import com.ordering.system.security.UserSpringSecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestsService {

    private final RequestsRepository requestsRepository;
    private final TicketService ticketService;
    private final PaymentRepository paymentRepository;
    private final ProductService productService;
    private final RequestItemRepository requestItemRepository;
    private final ClientRepository clientRepository;
    private final SendEmailService sendEmailService;


    public ResponseEntity<Requests> getRequestById(Integer id){
        Optional<Requests> request = this.requestsRepository.findById(id);

        if(request.isPresent()) return ResponseEntity.status(200).body(request.get());

        return ResponseEntity.status(404).body(null);
    }

    public Requests saveRequests(Requests requests){
        requests.setId(null);
        requests.setDateStart(new Date());
        requests.setClient(clientRepository.findById(requests.getClient().getId()).get());
        requests.getPayment().setStatus(PaymentStatus.PENDENT);
        requests.getPayment().setRequest(requests);

        if(requests.getPayment() instanceof PaymentTicket){
            PaymentTicket pagto = (PaymentTicket) requests.getPayment();
            ticketService.addPaymentWithTicket(pagto, requests.getDateStart());
        }

        requests = requestsRepository.save(requests);
        this.paymentRepository.save(requests.getPayment());

        for(RequestItem item: requests.getItens()){
            item.setOff(0.0);
            item.setProduct(this.productService.findProducttById(item.getProduct().getId()));
            item.setPrice(item.getProduct().getPrice());
            item.setRequest(requests);
        }
        this.requestItemRepository.saveAll(requests.getItens());
        this.sendEmailService.sendOrderConfirmationEmail(requests);
        return requests;
    }
    
    public List<Requests> findRequestByClient(){
        UserSpringSecurity user = UserService.authenticated();
        if(user == null) throw new AuthorizationException("Access denided");

        Optional<Client> client = clientRepository.findById(user.getId());
        if(client.isPresent()) return this.requestsRepository.findByClient(client.get());

        return null;
    }
}
