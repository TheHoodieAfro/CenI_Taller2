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
public class DocumentController {
	//------------------------------------------------------- Services -------------------------------------------------------
		DocumentServiceImp ds;
		
		TransactionhistoryServiceImp ths;
		
		ProductServiceImp ps;
		
		//------------------------------------------------------- Constructor -------------------------------------------------------
		@Autowired
		public DocumentController(DocumentServiceImp ds, TransactionhistoryServiceImp ths, ProductServiceImp ps) {
			this.ds = ds;
			this.ths = ths;
			this.ps = ps;
		}
		
		//------------------------------------------------------- Index -------------------------------------------------------
		@GetMapping("/document")
	    public String documents(Model model) {
			model.addAttribute("documents", ds.findAll());
	        return "operator/documents";
	    }
		
		//------------------------------------------------------- Consult -------------------------------------------------------
		
		
		//------------------------------------------------------- Save -------------------------------------------------------
		@GetMapping("/document/add")
		public String addDocument(Model model) {
			model.addAttribute("document", new Document());
			model.addAttribute("products", ps.findAll());
			return "operator/addDocument";
		}
		
		@PostMapping("/document/add")
		public String saveDocument(@Validated Document document, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
			if (!action.equals("Cancel")) {
				return "redirect:/document";
				
			}
			
			if(bindingResult.hasErrors()) {
				model.addAttribute("document", new Document());
				model.addAttribute("products", ps.findAll());
				return "operator/addDocument";
			}
			
			ds.save(document);
			return "redirect:/document";
		}
		
		//------------------------------------------------------- Edit -------------------------------------------------------
		
		
		//------------------------------------------------------- Delete -------------------------------------------------------
		@GetMapping("/document/delete/{id}")
		public String deleteDocument(@PathVariable("id") Integer id, Model model) {
			Optional<Document> document = ds.findById(id);
			if (document.isEmpty())
				throw new IllegalArgumentException("Invalid product Id:" + id);
			
			
			ds.delete(document.get());
			return "redirect:/document";
		}

		//------------------------------------------------------- Extra methods -------------------------------------------------------
		
}
