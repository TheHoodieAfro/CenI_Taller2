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

import com.taller.model.Product;
import com.taller.model.Productcategory;
import com.taller.model.Productsubcategory;
import com.taller.model.Productvendor;
import com.taller.model.Unitmeasure;
import com.taller.model.Vendor;
import com.taller.model.info;
import com.taller.service.implementations.ProductServiceImp;
import com.taller.service.implementations.ProductcategoryServiceImp;
import com.taller.service.implementations.ProductsubcategoryServiceImp;
import com.taller.service.implementations.ProductvendorServiceImp;
import com.taller.service.implementations.UnitmeasureServiceImp;
import com.taller.service.implementations.VendorServiceImp;

@Controller
public class ProductvendorController {
	//------------------------------------------------------- Services -------------------------------------------------------
		ProductServiceImp ps;
		ProductcategoryServiceImp pcs;
		ProductsubcategoryServiceImp pscs;
		UnitmeasureServiceImp ums;
		
		ProductvendorServiceImp pvs;
		VendorServiceImp vs;
		
		//------------------------------------------------------- Constructor -------------------------------------------------------
		@Autowired
		public ProductvendorController(ProductServiceImp ps, ProductvendorServiceImp pvs, VendorServiceImp vs, UnitmeasureServiceImp ums, ProductcategoryServiceImp pcs, ProductsubcategoryServiceImp pscs) {
			this.ps = ps;
			this.pvs = pvs;
			this.vs = vs;
			this.ums = ums;
			this.pcs = pcs;
			this.pscs = pscs;
		}
		
		//------------------------------------------------------- Index -------------------------------------------------------
		@GetMapping("/productvendor")
	    public String Productvendors(Model model) {
			model.addAttribute("productvendors", pvs.findAll());
	        return "admin/productvendors";
	    }
		
		//------------------------------------------------------- Consult -------------------------------------------------------
		/*@GetMapping("/product/{id}")
	    public String consultProductsByProductvendor(@PathVariable("id") Integer id, Model model) {
			model.addAttribute("products", ps.find());
	        return "admin/products";
	    }*/
		
		//------------------------------------------------------- Save -------------------------------------------------------
		@GetMapping("/productvendor/add")
		public String addProductvendor(Model model) {
			model.addAttribute("productvendor", new Productvendor());
			model.addAttribute("vendors", vs.findAll());
			model.addAttribute("products", ps.findAll());
			return "admin/addProductvendor";
		}
		
		@PostMapping("/productvendor/add")
		public String saveProductvendor(@Validated Productvendor productvendor, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
			if (action.equals("Cancel")) {
				return "redirect:/productvendor";
			}
			
			if(bindingResult.hasErrors()) {
				model.addAttribute("productvendor", new Productvendor());
				model.addAttribute("vendors", vs.findAll());
				model.addAttribute("products", ps.findAll());
				return "admin/addProductvendor";
			}
				
			pvs.save(productvendor);
			return "redirect:/productvendor";
		}
		
		//------------------------------------------------------- Edit -------------------------------------------------------
		@GetMapping("/productvendor/edit/{id}")
		public String editProductvendor(@PathVariable("id") Integer id, Model model) {
			Productvendor pv = pvs.findById(id).get();
			if (pv == null)
				throw new IllegalArgumentException("Invalid product vendor Id:" + id);
			
			model.addAttribute("productvendor", pv);
			model.addAttribute("vendors", vs.findAll());
			model.addAttribute("products", ps.findAll());
			return "admin/editProductvendor";
		}
		
		@PostMapping("/productvendor/edit/{id}")
		public String updateProductvendor(@PathVariable("id") Integer id, @Validated(info.class) Productvendor productvendor, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
			if (action.equals("Cancel")) {
				return "redirect:/productvendor";
			}
			if(bindingResult.hasErrors()) {
				Productvendor pv = pvs.findById(id).get();
				if (pv == null)
					throw new IllegalArgumentException("Invalid product vendor Id:" + id);
				
				model.addAttribute("productvendor", pv);
				model.addAttribute("vendors", vs.findAll());
				model.addAttribute("products", ps.findAll());
				return "admin/editProductvendor";
			}
			productvendor.setId(id);
			pvs.update(productvendor);
			return "redirect:/productvendor";
		}
		
		//------------------------------------------------------- Delete -------------------------------------------------------
		
		@GetMapping("/productvendor/delete/{id}")
		public String deleteProductvendor(@PathVariable("id") Integer id, Model model) {
			Optional<Productvendor> productvendor = pvs.findById(id);
			if (productvendor.isEmpty())
				throw new IllegalArgumentException("Invalid product vendor Id:" + id);
			
			
			pvs.delete(productvendor.get());
			return "redirect:/productvendor";
		}
		
		//------------------------------------------------------- Extra methods -------------------------------------------------------
		
}
