package com.taller.service.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.model.Document;
import com.taller.model.Product;
import com.taller.model.Productdocument;
import com.taller.repository.interfaces.DocumentRepository;
import com.taller.repository.interfaces.ProductRepository;
import com.taller.service.interfaces.DocumentService;

@Service
public class DocumentServiceImp implements DocumentService {
	
	private DocumentRepository dr;

	@Autowired
	public DocumentServiceImp(DocumentRepository dr) {
		this.dr = dr;
	}
	
	public Iterable<Document> findAll() {
		return dr.findAll();
	}
	
	@Override
	public Document save(Document doc) {
		return dr.save(doc);
	}
	
	@Override
	public Document edit(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Document> findById(Integer id) {
		// TODO Auto-generated method stub
		return dr.findById(id);
	}

	public void delete(Document document) {
		dr.deleteById(document.getDocumentnode());
	}

	public void update(Document dd) {
		Document d = dr.findById(dd.getDocumentnode()).get();
		
		d.setFileextension(dd.getFileextension());
		d.setFilename(dd.getFilename());
		d.setModifieddate(LocalDate.now());
		d.setTitle(dd.getTitle());
		
		dr.save(d);
	}

	public Iterable<Product> findProductsByDocument(Integer id) {
		List<Productdocument> pds = dr.findById(id).get().getProductdocuments();
		
		List<Product> ps = new ArrayList<Product>();
		for(Productdocument pd : pds) {
			ps.add(pd.getProduct());
		}
		
		Iterable<Product> ret = ps;
		return ret;
	}
	
}
