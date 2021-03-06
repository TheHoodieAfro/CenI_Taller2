package com.taller.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.model.Product;
import com.taller.model.Vendor;
import com.taller.repository.interfaces.ProductRepository;
import com.taller.repository.interfaces.VendorRepository;
import com.taller.service.interfaces.ProductService;
import com.taller.service.interfaces.ProductvendorService;
import com.taller.service.interfaces.VendorService;

@Service
public class VendorServiceImp implements VendorService{

	private VendorRepository vr;

	@Autowired
	public VendorServiceImp(VendorRepository vr) {
		this.vr = vr;
	}
	
	public Iterable<Vendor> findAll() {
		return vr.findAll();
	}
	
	@Override
	public Vendor save(Vendor vendor) {
		// TODO Auto-generated method stub
		return vr.save(vendor);
	}

	@Override
	public Vendor edit(Vendor vendor) {
		// TODO Auto-generated method stub
		return null;
	}

}
