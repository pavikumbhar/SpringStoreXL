package com.pavikumbhar.springmvc.store.beans;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.pavikumbhar.springmvc.store.controller.CategoryRestController;

@XmlRootElement
public class CategoryList {
	private List<Category> categories = new ArrayList<Category>();
	
	public CategoryList(List<com.pavikumbhar.springmvc.store.model.Category> modelCategories) {
		
		for (com.pavikumbhar.springmvc.store.model.Category modelCategory : modelCategories ) {
			Category c = new Category();
			c.setName(modelCategory.getName());
			c.setDescription(modelCategory.getDescription());
			c.add(linkTo(CategoryRestController.class).slash(modelCategory.getId()).withSelfRel());
			categories.add(c);
		}
	}

	
	
	public List<Category> getCategories() {
		return categories;
	}



	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}



	public class Category extends ResourceSupport {
		
		private String name;

	    private String description;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}
}
