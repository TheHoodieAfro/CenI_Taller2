package com.taller.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.taller.model.Productcategory;
import com.taller.model.Productsubcategory;
import com.taller.model.Unitmeasure;
import com.taller.model.Vendor;
import com.taller.repository.interfaces.ProductcategoryRepository;
import com.taller.repository.interfaces.ProductsubcategoryRepository;
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
	public CommandLineRunner add(ProductcategoryRepository pcr, ProductsubcategoryRepository pscr, UnitmeasureRepository umr, VendorRepository vr) {
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
		};
	}

}