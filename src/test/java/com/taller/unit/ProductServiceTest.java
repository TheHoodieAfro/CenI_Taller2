package com.taller.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.taller.boot.Taller1Application;
import com.taller.model.Product;
import com.taller.model.Productcategory;
import com.taller.model.Productsubcategory;
import com.taller.model.Unitmeasure;
import com.taller.repository.interfaces.ProductRepository;
import com.taller.repository.interfaces.ProductcategoryRepository;
import com.taller.repository.interfaces.ProductsubcategoryRepository;
import com.taller.repository.interfaces.ProductvendorRepository;
import com.taller.repository.interfaces.UnitmeasureRepository;
import com.taller.service.implementations.ProductServiceImp;
import com.taller.service.interfaces.ProductService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller1Application.class)
class ProductServiceTest {
	
	@Mock
	private ProductRepository pr;
	
	@Mock
	private ProductcategoryRepository cr;
	
	@Mock
	private ProductsubcategoryRepository scr;
	
	@Mock
	private UnitmeasureRepository umr;
	
	@Mock
	private ProductvendorRepository pvr;
	
	@InjectMocks
	private ProductService ps;
	
	@Autowired
	public ProductServiceTest() {
		this.ps = new ProductServiceImp(pr, umr, scr, pvr);
	}

	@Nested
	@DisplayName("Save products")
	class save {
		
		Optional<Productcategory> c;
		Optional<Productsubcategory> sc;
		Unitmeasure um;
		Optional<Product> p;
		
		@BeforeEach
		public void creation() {
			c = Optional.of(new Productcategory());
			c.get().setProductsubcategories(new ArrayList<Productsubcategory>());
			c.get().setProductcategoryid(1);
			
			sc = Optional.of(new Productsubcategory());
			sc.get().setProducts(new ArrayList<Product>());
			sc.get().setProductsubcategoryid(1);
			when(scr.findById(1)).thenReturn(sc);
			
			c.get().addProductsubcategory(scr.findById(1).get());
			
			um = new Unitmeasure();
			
			p = Optional.of(new Product());
			p.get().setProductid(1);
			sc.get().addProduct(p.get());
			p.get().setUnitmeasure1(um);
			p.get().setUnitmeasure2(um);
			//p.get().setSellstartdate(new Timestamp(1));
			//p.get().setSellenddate(new Timestamp(2));
			p.get().setDaystomanufacture(5);
			when(pr.save(p.get())).thenReturn(p.get());
		}
		
		@Test
		public void saveNormal() {
			assertEquals(ps.save(p.get()).getProductid(), p.get().getProductid());
			
			verify(pr, times(1)).save(p.get());
		}
		
		@Test
		public void saveStartDateOverFinishDate() {
			//p.get().setSellstartdate(new Timestamp(2));
			//p.get().setSellenddate(new Timestamp(1));
			
			assertNull(ps.save(p.get()));
			
			verify(pr, times(0)).save(p.get());
		}
		
		@Test
		public void saveDaysToManufactureCero() {
			p.get().setDaystomanufacture(0);
			
			assertNull(ps.save(p.get()));
			
			verify(pr, times(0)).save(p.get());
		}
		
	}
	
	@Nested
	@DisplayName("Edit products")
	class edit {
		
		@Test
		public void edit() {
		}
		
	}

}
