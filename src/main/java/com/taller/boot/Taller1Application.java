package com.taller.boot;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.taller.model.Product;
import com.taller.model.Productcategory;
import com.taller.model.Productsubcategory;
import com.taller.model.Transactionhistory;
import com.taller.model.Unitmeasure;
import com.taller.model.Vendor;
import com.taller.repository.interfaces.ProductRepository;
import com.taller.repository.interfaces.ProductcategoryRepository;
import com.taller.repository.interfaces.ProductsubcategoryRepository;
import com.taller.repository.interfaces.TransactionhistoryRepository;
import com.taller.repository.interfaces.UnitmeasureRepository;
import com.taller.repository.interfaces.VendorRepository;

@SpringBootApplication
@EnableJpaRepositories("com.taller.repository.interfaces")
@EntityScan(basePackages = {"com.taller.auth","com.taller.model"})
@ComponentScan(basePackages = {"com.taller.auth","com.taller.repository.interfaces","com.taller.service.implementations","com.taller.controller"})
public class Taller1Application {

	public static void main(String[] args) {
		SpringApplication.run(Taller1Application.class, args);
	}
	
	@Bean
	public CommandLineRunner add(TransactionhistoryRepository thr, ProductcategoryRepository pcr, ProductsubcategoryRepository pscr, UnitmeasureRepository umr, VendorRepository vr, ProductRepository pr) {
		return (args) -> {
			Productcategory pc1 = new Productcategory();
			pc1.setName("Comida");
			pcr.save(pc1);
			Productcategory pc2 = new Productcategory();
			pc2.setName("Drogas");
			pcr.save(pc2);
			
			Productsubcategory psc1 = new Productsubcategory();
			psc1.setName("Fruta");
			psc1.setProductcategory(pc1);
			pscr.save(psc1);
			Productsubcategory psc2 = new Productsubcategory();
			psc2.setName("Chatarra");
			psc2.setProductcategory(pc1);
			pscr.save(psc2);
			Productsubcategory psc3 = new Productsubcategory();
			psc3.setName("Coca");
			psc3.setProductcategory(pc2);
			pscr.save(psc3);
			Productsubcategory psc4 = new Productsubcategory();
			psc4.setName("Mari");
			psc4.setProductcategory(pc2);
			pscr.save(psc4);
			
			Unitmeasure um1 = new Unitmeasure();
			um1.setName("kg");
			umr.save(um1);
			Unitmeasure um2 = new Unitmeasure();
			um2.setName("pounds");
			umr.save(um2);
			Unitmeasure um3 = new Unitmeasure();
			um3.setName("m");
			umr.save(um3);
			Unitmeasure um4 = new Unitmeasure();
			um4.setName("inches");
			umr.save(um4);
			
			Vendor v1 = new Vendor();
			v1.setName("Cristian Sanchez");
			vr.save(v1);
			Vendor v2 = new Vendor();
			v2.setName("dotor uribito");
			vr.save(v2);
			
			Product p1 = new Product();
			p1.setName("Piña");
			p1.setDaystomanufacture(360);
			p1.setProductnumber("123");
			p1.setProductsubcategory(psc1);
			p1.setUnitmeasure1(um3);
			p1.setUnitmeasure2(um1);
			pr.save(p1);
			Product p4 = new Product();
			p4.setName("anvorgesa");
			p4.setDaystomanufacture(1);
			p4.setProductnumber("937");
			p4.setProductsubcategory(psc2);
			p4.setUnitmeasure1(um3);
			p4.setUnitmeasure2(um2);
			pr.save(p4);
			Product p2 = new Product();
			p2.setName("perico");
			p2.setDaystomanufacture(30);
			p2.setProductnumber("666");
			p2.setProductsubcategory(psc3);
			p2.setUnitmeasure1(um4);
			p2.setUnitmeasure2(um2);
			pr.save(p2);
			Product p3 = new Product();
			p3.setName("guana");
			p3.setDaystomanufacture(90);
			p3.setProductnumber("420");
			p3.setProductsubcategory(psc4);
			p3.setUnitmeasure1(um4);
			p3.setUnitmeasure2(um1);
			pr.save(p3);
			
			Transactionhistory th1 = new Transactionhistory();
			th1.setActualcost(BigDecimal.valueOf(10000));
			th1.setQuantity(2);
			th1.setTransactiondate(LocalDate.of(2021, 11, 1));
			th1.setModifieddate(LocalDate.of(2021, 11, 2));
			th1.setProduct(p1);
			thr.save(th1);
			Transactionhistory th2 = new Transactionhistory();
			th2.setActualcost(BigDecimal.valueOf(15000));
			th2.setQuantity(3);
			th2.setTransactiondate(LocalDate.of(2021, 11, 4));
			th2.setModifieddate(LocalDate.of(2021, 11, 6));
			th2.setProduct(p1);
			thr.save(th2);
			Transactionhistory th3 = new Transactionhistory();
			th3.setActualcost(BigDecimal.valueOf(5000));
			th3.setQuantity(1);
			th3.setTransactiondate(LocalDate.of(2021, 11, 1));
			th3.setModifieddate(LocalDate.of(2021, 11, 10));
			th3.setProduct(p1);
			thr.save(th3);
			Transactionhistory th4 = new Transactionhistory();
			th4.setActualcost(BigDecimal.valueOf(50000));
			th4.setQuantity(1);
			th4.setTransactiondate(LocalDate.of(2021, 11, 1));
			th4.setModifieddate(LocalDate.of(2021, 11, 10));
			th4.setProduct(p2);
			thr.save(th4);
		};
	}

}