package com.ordering.system.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.ordering.system.domains.*;
import com.ordering.system.enums.ClientType;
import com.ordering.system.enums.PaymentStatus;
import com.ordering.system.repositories.AdressRepository;
import com.ordering.system.repositories.CategoryRepository;
import com.ordering.system.repositories.CityRepository;
import com.ordering.system.repositories.ClientRepository;
import com.ordering.system.repositories.PaymentRepository;
import com.ordering.system.repositories.ProductRepository;
import com.ordering.system.repositories.RequestItemRepository;
import com.ordering.system.repositories.RequestsRepository;
import com.ordering.system.repositories.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {

        @Autowired
        private CategoryRepository categoryRepository;
        @Autowired
        private ProductRepository productRepository;
        @Autowired
        private CityRepository cityRepository;
        @Autowired
        private StateRepository stateRepository;
        @Autowired
        private ClientRepository clientRepository;
        @Autowired
        private AdressRepository adressRepository;
        @Autowired
        private RequestsRepository requestsRepository;
        @Autowired
        private PaymentRepository paymentRepository;
        @Autowired
        private RequestItemRepository requestItemRepository;
        @Autowired
        BCryptPasswordEncoder bCryptPasswordEncoder;

        public void instantiateTestDatabase() throws ParseException {
                Category cat1 = new Category();
                cat1.setId(null);
                cat1.setName("Informática");

                Category cat2 = new Category();
                cat2.setId(null);
                cat2.setName("Escritório");

                Category cat3 = new Category();
                cat3.setId(null);
                cat3.setName("Cama mesa banho");

                Category cat4 = new Category();
                cat4.setId(null);
                cat4.setName("Eletrônicos");

                Category cat5 = new Category();
                cat5.setId(null);
                cat5.setName("Jardinagem");

                Category cat6 = new Category();
                cat6.setId(null);
                cat6.setName("Decoração");

                Category cat7 = new Category();
                cat7.setId(null);
                cat7.setName("Perfumaria");

                Product p1 = new Product();
                p1.setId(null);
                p1.setName("Computador");
                p1.setPrice(2000.00);

                Product p2 = new Product();
                p2.setId(null);
                p2.setName("Impressora");
                p2.setPrice(800.00);

                Product p3 = new Product();
                p3.setId(null);
                p3.setName("mouse");
                p3.setPrice(80.00);
                Product p4 = new Product();
                p4.setId(null);
                p4.setName("mesa de escritório");
                p4.setPrice(300.00);
                Product p5 = new Product();
                p5.setId(null);
                p5.setName("toalha");
                p5.setPrice(50.00);
                Product p6 = new Product();
                p6.setId(null);
                p6.setName("colcha");
                p6.setPrice(200.00);
                Product p7 = new Product();
                p7.setId(null);
                p7.setName("TV full hd");
                p7.setPrice(1900.00);
                Product p8 = new Product();
                p8.setId(null);
                p8.setName("roçadeira");
                p8.setPrice(800.00);
                Product p9 = new Product();
                p9.setId(null);
                p9.setName("abajur");
                p9.setPrice(100.00);
                Product p10 = new Product();
                p10.setId(null);
                p10.setName("Guarda roupa");
                p10.setPrice(700.00);
                Product p11 = new Product();
                p11.setId(null);
                p11.setName("shampoo");
                p11.setPrice(90.00);

                cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
                cat2.getProducts().addAll(Arrays.asList(p2, p4));
                cat3.getProducts().addAll(Arrays.asList(p5, p6));
                cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
                cat5.getProducts().addAll(Arrays.asList(p8));
                cat6.getProducts().addAll(Arrays.asList(p9, p10));
                cat7.getProducts().addAll(Arrays.asList(p11));

                p1.getCategories().addAll(Arrays.asList(cat1, cat4));
                p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
                p3.getCategories().addAll(Arrays.asList(cat1, cat4));
                p4.getCategories().addAll(Arrays.asList(cat2));
                p5.getCategories().addAll(Arrays.asList(cat3));
                p6.getCategories().addAll(Arrays.asList(cat3));
                p7.getCategories().addAll(Arrays.asList(cat4));
                p8.getCategories().addAll(Arrays.asList(cat5));
                p9.getCategories().addAll(Arrays.asList(cat6));
                p10.getCategories().addAll(Arrays.asList(cat6));
                p11.getCategories().addAll(Arrays.asList(cat7));

                this.categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
                this.productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

                State state1 = new State();
                State state2 = new State();
                state1.setName("São Paulo");
                state2.setName("Minas Gerais");

                City c1 = new City(null, "Franca", state1);
                City c2 = new City(null, "Uberlandia", state2);
                City c3 = new City(null, "Campinas", state1);

                state1.getCities().addAll(Arrays.asList(c1, c3));
                state2.getCities().addAll(Arrays.asList(c2));

                this.stateRepository.saveAll(Arrays.asList(state1, state2));
                this.cityRepository.saveAll(Arrays.asList(c1, c2, c3));

                Client client1 = new Client(null, "João Flávio", "flaviosenne123@gmail.com", "123.456.789-00",
                                ClientType.PHYSICAL, bCryptPasswordEncoder.encode("123"));

                client1.getPhone().addAll(Arrays.asList("(16)9999-9087", "(16)98583-2314"));

                Adress adress1 = new Adress(null, "Rua das amoras", "1234", "perto da esquina", "Jardim amazonas",
                                "14432-00", client1, c1);
                Adress adress2 = new Adress(null, "Avenida dos carros", "5623", "em frente a loja", "Jardim Salvação",
                                "16432-87", client1, c2);

                client1.getAdresses().addAll(Arrays.asList(adress1, adress2));

                this.clientRepository.saveAll(Arrays.asList(client1));
                this.adressRepository.saveAll(Arrays.asList(adress1, adress2));

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                Requests requests1 = new Requests(null, sdf.parse("30/09/2020 10:30"), client1, adress1);
                Requests requests2 = new Requests(null, sdf.parse("27/11/2020 09:30"), client1, adress2);

                Payment payment1 = new PaymentCard(null, PaymentStatus.PENDENT, requests1, 6);
                requests1.setPayment(payment1);

                Payment payment2 = new PaymentTicket(null, PaymentStatus.PENDENT, requests2,
                                sdf.parse("21/02/2020 00:00"), null);
                requests2.setPayment(payment2);

                client1.getRequests().addAll(Arrays.asList(requests1, requests2));

                this.requestsRepository.saveAll(Arrays.asList(requests1, requests2));
                this.paymentRepository.saveAll(Arrays.asList(payment1, payment2));

                RequestItem item1 = new RequestItem(p1, requests1, 0.00, 1, 2000.00);
                RequestItem item2 = new RequestItem(p3, requests1, 0.00, 2, 80.00);
                RequestItem item3 = new RequestItem(p2, requests2, 100.00, 1, 800.00);

                requests1.getItens().addAll(Arrays.asList(item1, item2));
                requests2.getItens().addAll(Arrays.asList(item3));

                p1.getItens().addAll(Arrays.asList(item1));
                p2.getItens().addAll(Arrays.asList(item3));
                p3.getItens().addAll(Arrays.asList(item2));

                this.requestItemRepository.saveAll(Arrays.asList(item1, item2, item3));
        }
}
