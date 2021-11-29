package com.taller.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller.model.Document;
import com.taller.model.Transactionhistory;
import com.taller.model.info;
import com.taller.service.implementations.DocumentServiceImp;
import com.taller.service.implementations.ProductServiceImp;
import com.taller.service.implementations.TransactionhistoryServiceImp;

@Controller
public class TransactionhistoryController {
	//------------------------------------------------------- Services -------------------------------------------------------
		DocumentServiceImp ds;
		
		TransactionhistoryServiceImp ths;
		
		ProductServiceImp ps;
		
		//------------------------------------------------------- Constructor -------------------------------------------------------
		@Autowired
		public TransactionhistoryController(DocumentServiceImp ds, TransactionhistoryServiceImp ths, ProductServiceImp ps) {
			this.ds = ds;
			this.ths = ths;
			this.ps = ps;
		}
		
		//------------------------------------------------------- Index -------------------------------------------------------
		@GetMapping("/transactionhistory")
	    public String transactionhistories(Model model) {
			model.addAttribute("transactionhistories", ths.findAll());
	        return "operator/transactionhistories";
	    }
		
		//------------------------------------------------------- Consult -------------------------------------------------------
		/*@GetMapping("/Transactionhistory/{id}")
	    public String consultTransactionhistoryByProduct(@PathVariable("id") Integer id, Model model) {
			model.addAttribute("transactionhistories", ths.findByProducts(id));
	        return "operator/transactionhistoriesByProduct";
	    }*/
		
		@GetMapping("/Transactionhistory/{id}")
	    public String consultTransactionhistoryByProduct2(@PathVariable("id") Integer id, Model model) {
			model.addAttribute("transactionhistories", ths.findByProducts2(id));
	        return "operator/transactionhistoriesByProduct";
	    }
		
		//------------------------------------------------------- Save -------------------------------------------------------
		@GetMapping("/transactionhistory/add")
		public String addTransactionHistory(Model model) {
			model.addAttribute("transactionhistory", new Transactionhistory());
			model.addAttribute("products", ps.findAll());
			return "operator/addTransactionhistory";
		}
		
		@PostMapping("/transactionhistory/add")
		public String saveTransactionHistory(Transactionhistory transactionhistory, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
			if (action.equals("Cancel")) {
				return "redirect:/transactionhistory";
			}
			
			if(bindingResult.hasErrors()) {
				model.addAttribute("transactionhistory", new Transactionhistory());
				model.addAttribute("products", ps.findAll());
				return "operator/addTransactionhistory";
			}
			
			ths.save(transactionhistory);
			return "redirect:/transactionhistory";
		}
		
		//------------------------------------------------------- Edit -------------------------------------------------------
		@PostMapping("/transactionhistory/edit/{id}")
		public String updateTransactionHistory(@PathVariable("id") Integer id, @Validated(info.class) Transactionhistory transactionhistory, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
			if (action.equals("Cancel")) {
				return "redirect:/transactionhistory";
			}
			if(bindingResult.hasErrors()) {
				Transactionhistory th = ths.findById(id).get();
				if (th == null)
					throw new IllegalArgumentException("Invalid transaction history Id:" + id);
				
				model.addAttribute("transactionhistory", th);
				model.addAttribute("products", ps.findAll());
				return "operator/editTransactionhistory";
			}
			transactionhistory.setTransactionid(id);
			ths.update(transactionhistory);
			return "redirect:/transactionhistory";
		}
		
		//------------------------------------------------------- Delete -------------------------------------------------------
		@GetMapping("/transactionhistory/delete/{id}")
		public String deleteTransactionhistory(@PathVariable("id") Integer id, Model model) {
			Optional<Transactionhistory> transactionhistory = ths.findById(id);
			if (transactionhistory.isEmpty())
				throw new IllegalArgumentException("Invalid transaction history Id:" + id);
			
			
			ths.delete(transactionhistory.get());
			return "redirect:/transactionhistory";
		}
		
		//------------------------------------------------------- Extra methods -------------------------------------------------------
		
}
