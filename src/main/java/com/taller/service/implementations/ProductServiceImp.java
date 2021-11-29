package com.taller.service.implementations;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.model.Product;
import com.taller.model.Productsubcategory;
import com.taller.repository.interfaces.ProductRepository;
import com.taller.repository.interfaces.ProductsubcategoryRepository;
import com.taller.repository.interfaces.ProductvendorRepository;
import com.taller.repository.interfaces.UnitmeasureRepository;
import com.taller.service.interfaces.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	private ProductRepository pr;
	
	private UnitmeasureRepository umr;
	
	private ProductsubcategoryRepository pscr;
	
	private ProductvendorRepository pvr;
	
	@Autowired
	public ProductServiceImp(ProductRepository pr, UnitmeasureRepository umr, ProductsubcategoryRepository pscr, ProductvendorRepository pvr) {
		this.pr = pr;
		this.umr = umr;
		this.pscr = pscr;
		this.pvr = pvr;
	}
	
	public Iterable<Product> findAll() {
		return pr.findAll();
	}
	
	public Optional<Product> findById(Integer id) {
		return pr.findById(id);
	}
	/*
	public Iterable<Product> findAllById(Integer id) {
		ArrayList<Product> p = (ArrayList<Product>) pr.findAll();
		Integer pv = pvr.findById(id).get().getId();
		ArrayList<Product> toReturn = new ArrayList<Product>();
		
		for (Product pp : p) {
			if(pp.getBusinessentity().getName().equals(name)) {
				toReturn.add(pp);
			}
		}
		return toReturn;
	}*/
	
	@Override
	public Product save(Product prod) {
		prod.setProductsubcategory(pscr.findById(prod.getProductsubcategory().getProductsubcategoryid()).get());
		prod.setUnitmeasure1(umr.findById(prod.getUnitmeasure1().getUnitmeasurecode()).get());
		prod.setUnitmeasure2(umr.findById(prod.getUnitmeasure2().getUnitmeasurecode()).get());
		return pr.save(prod);
	}

	@Override
	public Product edit(Product prod) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void delete(Product prod) {
		pr.deleteById(prod.getProductid());
	}

	public void update(Product p) {
		Product mp = pr.findById(p.getProductid()).get();
		
		mp.setDaystomanufacture(p.getDaystomanufacture());
		mp.setName(p.getName());
		mp.setProductnumber(p.getProductnumber());
		mp.setProductsubcategory(pscr.findById(p.getProductsubcategory().getProductsubcategoryid()).get());
		mp.setUnitmeasure1(umr.findById(p.getUnitmeasure1().getUnitmeasurecode()).get());
		mp.setUnitmeasure2(umr.findById(p.getUnitmeasure2().getUnitmeasurecode()).get());
		
		pr.save(mp);
	}

}
