package com.taller.service.interfaces;

import com.taller.model.Document;
import com.taller.model.Productdocument;

public interface ProductdocumentService {
	public Productdocument save(Productdocument prod);
	
	public Productdocument edit(Productdocument prod);
}
