package com.pavikumbhar.springmvc.store.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pavikumbhar.springmvc.store.ProductSearchValidator;
import com.pavikumbhar.springmvc.store.model.Category;
import com.pavikumbhar.springmvc.store.model.Product;
import com.pavikumbhar.springmvc.store.service.CategoryService;
import com.pavikumbhar.springmvc.store.service.ProductService;

@Controller
@RequestMapping("/products")
public class SearchController {
	

	@Autowired
	ProductService productService;
	@Autowired 
	CategoryService categoryService; 
	@Autowired
    ProductSearchValidator productSearchValidator;
	
	
	@RequestMapping(value="/search")
	public String searchForm(Model model) {
		model.addAttribute("product", new Product());
		
        return "product/search";
    }
	
	@RequestMapping
	public String processSearch(@Valid Product product, BindingResult bindingResult, Model model) {
		
		
		if (bindingResult.hasErrors()) {
			return "product/search";
		}
		
		List<Product> results = productService.findProducts(product);
		if (results.size() < 1) {
			bindingResult.rejectValue("name", "notFound", "Sorry, no results found");
            return "product/search";
        }
		if (results.size() == 1) {
			product = results.get(0);
            return "redirect:/products/" + product.getId();
		}
		else {
            model.addAttribute("results", results);
            return "product/search";
		}
        
    }
	
	@ModelAttribute("categories")
	public List<Category> fetchAllCategories() {
		return categoryService.getAllCategories();
	}
	
    @InitBinder  
    private void initBinder(WebDataBinder binder) {  
        binder.setValidator(productSearchValidator); 
        binder.setAllowedFields("name", "category");
    }



}
