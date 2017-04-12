package com.pavikumbhar.springmvc.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavikumbhar.springmvc.store.model.Category;
import com.pavikumbhar.springmvc.store.dao.CategoryDao;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public long countAllCategorys() {
        return categoryDao.countCategories();
    }

	public void deleteCategory(Category category) {
		categoryDao.remove(category);
    }

	public Category findCategory(Long id) {
        return categoryDao.findCategory(id);
    }
	
	public Category findCategoryEagerly(Long id) {
        return categoryDao.findCategoryEagerly(id);
    }

	public List<Category> getAllCategories() {
        return categoryDao.findAllCategories();
    }

	public List<Category> findCategoryEntries(int firstResult, int maxResults) {
        return categoryDao.findCategoryEntries(firstResult, maxResults);
    }

	public void saveCategory(Category category) {
		categoryDao.persist(category);
    }

	public Category updateCategory(Category category) {
        return categoryDao.merge(category);
    }
}
