package com.pavikumbhar.springmvc.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavikumbhar.springmvc.store.model.Category;
import com.pavikumbhar.springmvc.store.model.Product;
import com.pavikumbhar.springmvc.store.dao.CategoryDao;
import com.pavikumbhar.springmvc.store.dao.ProductDao;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;
	
	
	public List<Product> getFeaturedProducts() {
		return productDao.findProductsByFeatured(true);
	}

	public long countAllProducts() {
        return productDao.countProducts();
    }

	public void deleteProduct(Product product) {
		productDao.remove(product);
    }

	public Product findProduct(Long id) {
         Product product = productDao.findProduct(id);
         if (product == null) {
        	 throw new RuntimeException();
         }
         return product;
    }

	public List<Product> findAllProducts() {
        return productDao.findAllProducts();
    }

	public List<Product> findProductEntries(int firstResult, int maxResults) {
        return productDao.findProductEntries(firstResult, maxResults);
    }

	public void saveProduct(Product product) {
		Category category = categoryDao.findCategory(product.getCategory().getId());
		product.setCategory(category);
		productDao.persist(product);
    }

	public Product updateProduct(Product product) {
        return productDao.merge(product);
    }

	public List<Product> findProducts(Product product) {
		return productDao.findProductEntries(product);
	}
}
