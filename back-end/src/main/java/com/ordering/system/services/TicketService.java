package com.ordering.system.services;

import java.util.Calendar;
import java.util.Date;

import com.ordering.system.domains.PaymentTicket;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    
    public void addPaymentWithTicket(PaymentTicket pagto, Date instant){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instant);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDueDate(cal.getTime());
    }
}
