/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.springmvc.store.dao;

import com.pavikumbhar.springmvc.store.model.Category;
import java.util.List;

/**
 *
 * @author pavikumbhar
 */
public interface CategoryDao {

    void clear();

    long countCategories();

    List<Category> findAllCategories();

    Category findCategory(Long id);

    Category findCategoryEagerly(Long id);

    List<Category> findCategoryEntries(int firstResult, int maxResults);

    void flush();

    Category merge(Category category);

    void persist(Category category);

    void remove(Category category);
    
}
