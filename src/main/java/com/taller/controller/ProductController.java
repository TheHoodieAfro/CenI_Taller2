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
import com.taller.model.info;
import com.taller.service.implementations.ProductServiceImp;
import com.taller.service.implementations.ProductcategoryServiceImp;
import com.taller.service.implementations.ProductsubcategoryServiceImp;
import com.taller.service.implementations.ProductvendorServiceImp;
import com.taller.service.implementations.UnitmeasureServiceImp;
import com.taller.service.implementations.VendorServiceImp;

@Controller
public class ProductController {
	//------------------------------------------------------- Services -------------------------------------------------------
		ProductServiceImp ps;
		ProductcategoryServiceImp pcs;
		ProductsubcategoryServiceImp pscs;
		UnitmeasureServiceImp ums;
		
		ProductvendorServiceImp pvs;
		VendorServiceImp vs;
		
		//------------------------------------------------------- Constructor -------------------------------------------------------
		@Autowired
		public ProductController(ProductServiceImp ps, ProductvendorServiceImp pvs, VendorServiceImp vs, UnitmeasureServiceImp ums, ProductcategoryServiceImp pcs, ProductsubcategoryServiceImp pscs) {
			this.ps = ps;
			this.pvs = pvs;
			this.vs = vs;
			this.ums = ums;
			this.pcs = pcs;
			this.pscs = pscs;
		}
		
		//------------------------------------------------------- Index -------------------------------------------------------
		@GetMapping("/product")
	    public String products(Model model) {
			model.addAttribute("products", ps.findAll());
	        return "admin/products";
	    }
		
		//------------------------------------------------------- Consult -------------------------------------------------------
		@GetMapping("/product/{id}")
		public String documentsByProduct(@PathVariable("id") Integer id, Model model) {
			model.addAttribute("documents", ps.findDocumentsByProduct(id));
			return "admin/documentsByProduct";
		}
		
		//------------------------------------------------------- Save -------------------------------------------------------
		@GetMapping("/product/add")
		public String addProduct(Model model) {
			model.addAttribute("product", new Product());
			model.addAttribute("productsubcategories", pscs.findAll());
			model.addAttribute("unitmeasures", ums.findAll());
			return "admin/addProduct";
		}
		
		@PostMapping("/product/add")
		public String saveProduct(@Validated(info.class) Product product, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
			if (action.equals("Cancel")) {
				return "redirect:/product";
			}
			if(bindingResult.hasErrors()) {
				model.addAttribute("product", new Product());
				model.addAttribute("productcategories", pcs.findAll());
				model.addAttribute("productsubcategories", pscs.findAll());
				model.addAttribute("unitmeasures", ums.findAll());
				return "admin/addProduct";
			}
			ps.save(product);
			return "redirect:/product";
		}
		
		//------------------------------------------------------- Edit -------------------------------------------------------
		@GetMapping("/product/edit/{id}")
		public String editProduct(@PathVariable("id") Integer id, Model model) {
			Product p = ps.findById(id).get();
			if (p == null)
				throw new IllegalArgumentException("Invalid product Id:" + id);
			
			model.addAttribute("product", p);
			model.addAttribute("productsubcategories", pscs.findAll());
			model.addAttribute("unitmeasures", ums.findAll());
			return "admin/editProduct";
		}
		
		@PostMapping("/product/edit/{id}")
		public String updateProduct(@PathVariable("id") Integer id, @Validated(info.class) Product product, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
			if (action.equals("Cancel")) {
				return "redirect:/product";
			}
			if(bindingResult.hasErrors()) {
				Product p = ps.findById(id).get();
				if (p == null)
					throw new IllegalArgumentException("Invalid product Id:" + id);
				
				model.addAttribute("product", p);
				model.addAttribute("productsubcategories", pscs.findAll());
				model.addAttribute("unitmeasures", ums.findAll());
				return "admin/editProduct";
			}
			product.setProductid(id);
			ps.update(product);
			return "redirect:/product";
		}
		
		//------------------------------------------------------- Delete -------------------------------------------------------
		@GetMapping("/product/delete/{id}")
		public String deleteProduct(@PathVariable("id") Integer id, Model model) {
			Optional<Product> product = ps.findById(id);
			if (product.isEmpty())
				throw new IllegalArgumentException("Invalid product Id:" + id);
			
			
			ps.delete(product.get());
			return "redirect:/product";
		}
		
		//------------------------------------------------------- Extra methods -------------------------------------------------------
		
		
}
