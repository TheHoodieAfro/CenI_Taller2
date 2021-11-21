package com.taller.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.model.Transactionhistory;
import com.taller.repository.interfaces.ProductRepository;
import com.taller.repository.interfaces.TransactionhistoryRepository;
import com.taller.service.interfaces.TransactionhistoryService;

@Service
public class TransactionhistoryServiceImp implements TransactionhistoryService {
	
	private TransactionhistoryRepository thr;
	
	private ProductRepository pr;

	@Autowired
	public TransactionhistoryServiceImp(TransactionhistoryRepository thr, ProductRepository pr) {
		this.thr = thr;
		this.pr = pr;
	}
	
	public Iterable<Transactionhistory> findAll() {
		return thr.findAll();
	}
	
	@Override
	public Transactionhistory save(Transactionhistory doc) {
		doc.setProduct(pr.findById(doc.getProduct().getProductid()).get());
		return thr.save(doc);
	}

	@Override
	public Transactionhistory edit(Transactionhistory doc) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Transactionhistory> findById(Integer id) {
		// TODO Auto-generated method stub
		return thr.findById(id);
	}

	public void delete(Transactionhistory transactionhistory) {
		// TODO Auto-generated method stub
		thr.deleteById(transactionhistory.getTransactionid());
	}

	public void update(Transactionhistory transactionhistory) {
		Transactionhistory mth = thr.findById(transactionhistory.getTransactionid()).get();
		
		mth.setActualcost(transactionhistory.getActualcost());
		mth.setQuantity(transactionhistory.getQuantity());
		mth.setTransactiondate(transactionhistory.getTransactiondate());
		mth.setModifieddate(transactionhistory.getModifieddate());
		mth.setProduct(pr.findById(transactionhistory.getProduct().getProductid()).get());
		
		thr.save(mth);
	}
	
}
