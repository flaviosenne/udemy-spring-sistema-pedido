package com.ordering.system.services;

import com.ordering.system.domains.PaymentTicket;
import com.ordering.system.domains.RequestItem;
import com.ordering.system.domains.Requests;
import com.ordering.system.enums.PaymentStatus;
import com.ordering.system.repositories.ClientRepository;
import com.ordering.system.repositories.PaymentRepository;
import com.ordering.system.repositories.RequestItemRepository;
import com.ordering.system.repositories.RequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RequestsService {
    @Autowired
    private RequestsRepository requestsRepository;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private RequestItemRepository requestItemRepository;
    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<Requests> getRequestById(Integer id){
        Optional<Requests> request = this.requestsRepository.findById(id);

        if(request.isPresent()){
            return ResponseEntity.status(200).body(request.get());
        }

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
        System.out.println(requests);
        return requests;
    }
}
