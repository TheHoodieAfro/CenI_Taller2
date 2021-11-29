package com.taller.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.model.Document;
import com.taller.model.Product;
import com.taller.model.Productdocument;
import com.taller.repository.interfaces.DocumentRepository;
import com.taller.repository.interfaces.ProductRepository;
import com.taller.repository.interfaces.ProductdocumentRepository;
import com.taller.repository.interfaces.ProductsubcategoryRepository;
import com.taller.repository.interfaces.ProductvendorRepository;
import com.taller.repository.interfaces.UnitmeasureRepository;
import com.taller.service.interfaces.ProductdocumentService;

@Service
public class ProductdocumentServiceImp implements ProductdocumentService {

	private ProductRepository pr;
	
	private DocumentRepository dr;
	
	private ProductdocumentRepository pdr;
	
	@Autowired
	public ProductdocumentServiceImp(ProductRepository pr, DocumentRepository dr, ProductdocumentRepository pdr) {
		this.pr = pr;
		this.dr = dr;
		this.pdr = pdr;
	}
	
	@Override
	public Productdocument save(Productdocument prod) {
		Document d = dr.findById(prod.getDocument().getDocumentnode()).get();
		Product p = pr.findById(prod.getProduct().getProductid()).get();
		
		d.addProductdocument(prod);
		p.addProductdocument(prod);
		
		dr.save(d);
		pr.save(p);

		return pdr.save(prod);
	}

	@Override
	public Productdocument edit(Productdocument prod) {
		// TODO Auto-generated method stub
		return null;
	}

}
