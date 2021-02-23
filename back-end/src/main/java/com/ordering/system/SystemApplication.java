package com.ordering.system;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemApplication implements CommandLineRunner{
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

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	Category cat1 = new Category();
	cat1.setId(null);
	cat1.setName("Informática");
	
	Category cat2 = new Category();
	cat2.setId(null);
	cat2.setName("Escritório");


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

	
	cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
	cat2.getProducts().addAll(Arrays.asList(p2));
	
	p1.getCategories().addAll(Arrays.asList(cat1));
	p2.getCategories().addAll(Arrays.asList(cat1, cat2));
	p3.getCategories().addAll(Arrays.asList(cat1));
	
	
	 this.categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	 this.productRepository.saveAll(Arrays.asList(p1,p2, p3));

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

	Client client1 = new Client(null, "João Silva", "joao@gmail.com", "123.456.789-00", ClientType.PHYSICAL);

	client1.getPhone().addAll(Arrays.asList("(16)9999-9087","(16)98583-2314"));
	
	Adress adress1 = new Adress(null, "Rua das amoras", "1234", "perto da esquina","Jardim amazonas", "14432-00", client1, c1);
	Adress adress2 = new Adress(null, "Avenida dos carros", "5623", "em frente a loja", "Jardim Salvação", "16432-87", client1, c2);

	client1.getAdresses().addAll(Arrays.asList(adress1, adress2));

	 this.clientRepository.saveAll(Arrays.asList(client1));
	 this.adressRepository.saveAll(Arrays.asList(adress1, adress2));

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	Requests requests1 = new Requests(null, sdf.parse("30/09/2020 10:30"), client1, adress1);
	Requests requests2 = new Requests(null, sdf.parse("27/11/2020 09:30"), client1, adress2);

	Payment payment1 = new PaymentCard(null, PaymentStatus.PENDENT, requests1, 6);
	requests1.setPayment(payment1);

	Payment payment2 = new PaymentTicket(null, PaymentStatus.PENDENT, requests2, sdf.parse("21/02/2020 00:00"), null);
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
