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
import com.taller.model.Productdocument;
import com.taller.model.info;
import com.taller.service.implementations.DocumentServiceImp;
import com.taller.service.implementations.ProductServiceImp;
import com.taller.service.implementations.ProductdocumentServiceImp;
import com.taller.service.implementations.TransactionhistoryServiceImp;

@Controller
public class OperatorController {
	//------------------------------------------------------- Services -------------------------------------------------------
			DocumentServiceImp ds;
			
			TransactionhistoryServiceImp ths;
			
			ProductServiceImp ps;

			ProductdocumentServiceImp pds;
			
			//------------------------------------------------------- Constructor -------------------------------------------------------
			@Autowired
			public OperatorController(DocumentServiceImp ds, TransactionhistoryServiceImp ths, ProductServiceImp ps, ProductdocumentServiceImp pds) {
				this.ds = ds;
				this.ths = ths;
				this.ps = ps;
				this.pds = pds;
			}
			
			//------------------------------------------------------- Index -------------------------------------------------------
			
			//------------------------------------------------------- Consult -------------------------------------------------------
			/*@GetMapping("/document/{id}")
			public String productsByDocuments(Model model) {
				return "redirect:/document";
			}*/
			
			//------------------------------------------------------- Save -------------------------------------------------------
			@GetMapping("/productdocument/add")
			public String addProductdocument(Model model) {
				model.addAttribute("productdocument", new Productdocument());
				model.addAttribute("products", ps.findAll());
				model.addAttribute("documents", ds.findAll());
				return "operator/addProductdocument";
			}
			
			@PostMapping("/productdocument/add")
			public String saveProductdocument(@Validated(info.class) Productdocument productdocument, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
				if (action.equals("Cancel")) {
					return "redirect:/document";
					
				}
				
				if(bindingResult.hasErrors()) {
					model.addAttribute("productdocument", new Productdocument());
					model.addAttribute("products", ps.findAll());
					model.addAttribute("documents", ds.findAll());
					return "operator/addProductdocument";
				}
				
				pds.save(productdocument);
				return "redirect:/document";
			}
			
			//------------------------------------------------------- Edit -------------------------------------------------------
			
			
			//------------------------------------------------------- Delete -------------------------------------------------------
			

			//------------------------------------------------------- Extra methods -------------------------------------------------------
			
}
