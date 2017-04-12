package com.pavikumbhar.springmvc.store.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pavikumbhar.springmvc.store.model.Category;
import com.pavikumbhar.springmvc.store.service.CategoryService;
import com.pavikumbhar.springmvc.store.service.ProductService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	ProductService productService;
	@Autowired 
	CategoryService categoryService; 
	
    
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		return new ModelAndView("home", "featuredProducts", productService.getFeaturedProducts());
	}
	
	
	@ModelAttribute("allCategories")
	public List<Category> fetchAllCategories() {
		return categoryService.getAllCategories();
	}
	
}
