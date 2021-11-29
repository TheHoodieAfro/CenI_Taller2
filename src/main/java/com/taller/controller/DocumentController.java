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
import com.taller.model.Product;
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
		@GetMapping("/document/{id}")
		public String productsByDocuments(@PathVariable("id") Integer id, Model model) {
			model.addAttribute("products", ds.findProductsByDocument(id));
			return "operator/productsByDocument";
		}
		
		//------------------------------------------------------- Save -------------------------------------------------------
		@GetMapping("/document/add")
		public String addDocument(Model model) {
			model.addAttribute("document", new Document());
			return "operator/addDocument";
		}
		
		@PostMapping("/document/add")
		public String saveDocument(@Validated(info.class) Document document, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
			if (action.equals("Cancel")) {
				return "redirect:/document";
				
			}
			
			if(bindingResult.hasErrors()) {
				model.addAttribute("document", new Document());
				return "operator/addDocument";
			}
			
			ds.save(document);
			return "redirect:/document";
		}
		
		//------------------------------------------------------- Edit -------------------------------------------------------
		@GetMapping("/document/edit/{id}")
		public String editDocument(@PathVariable("id") Integer id, Model model) {
			Document p = ds.findById(id).get();
			if (p == null)
				throw new IllegalArgumentException("Invalid Document Id:" + id);
			
			model.addAttribute("document", p);
			return "operator/editDocument";
		}
		
		@PostMapping("/document/edit/{id}")
		public String updateDocument(@PathVariable("id") Integer id, @Validated(info.class) Document document, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
			if (action.equals("Cancel")) {
				return "redirect:/document";
			}
			if(bindingResult.hasErrors()) {
				Document p = ds.findById(id).get();
				if (p == null)
					throw new IllegalArgumentException("Invalid Document Id:" + id);
				
				model.addAttribute("document", p);
				return "operator/editDocument";
			}
			document.setDocumentnode(id);
			ds.update(document);
			return "redirect:/document";
		}
		
		//------------------------------------------------------- Delete -------------------------------------------------------
		@GetMapping("/document/delete/{id}")
		public String deleteDocument(@PathVariable("id") Integer id, Model model) {
			Optional<Document> document = ds.findById(id);
			if (document.isEmpty())
				throw new IllegalArgumentException("Invalid document Id:" + id);
			
			
			ds.delete(document.get());
			return "redirect:/document";
		}

		//------------------------------------------------------- Extra methods -------------------------------------------------------
		
}
