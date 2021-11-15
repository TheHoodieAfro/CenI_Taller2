package com.taller.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.model.Product;
import com.taller.repository.interfaces.ProductRepository;
import com.taller.service.interfaces.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	private ProductRepository pr;

	@Autowired
	public ProductServiceImp(ProductRepository pr) {
		this.pr = pr;
	}
	
	@Override
	public Product save(Product prod) {
		
		if(prod.getSellstartdate().after(prod.getSellenddate()) ||
				prod.getDaystomanufacture() == 0 ||
				prod.getProductsubcategory() == null ||
				prod.getProductsubcategory().getProductcategory() == null ||
				prod.getUnitmeasure1() == null) {
			return null;
		}
		
		return pr.save(prod);
	}

	@Override
	public Product edit(Product prod) {
		// TODO Auto-generated method stub
		return null;
	}

}
