package com.pavikumbhar.springmvc.store.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pavikumbhar.springmvc.store.model.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    //@PersistenceContext
    //private EntityManager entityManager;

    
        @Autowired
      SessionFactory sessionFactory;
	

    @Override
	public long countCategories() {
              Long count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(o) FROM Category o").uniqueResult();
        return count;
      
      //  return entityManager.createQuery("SELECT COUNT(o) FROM Category o", Long.class).getSingleResult();
    }

    @Override
	public List<Category> findAllCategories() {
              return sessionFactory.getCurrentSession().createQuery("SELECT o FROM Category o").list();   
        //return entityManager.createQuery("SELECT o FROM Category o", Category.class).getResultList();
    }

    @Override
	public Category findCategory(Long id) {
        if (id == null) return null;
           return (Category)sessionFactory.getCurrentSession().get(Category.class, id);

        //return entityManager.find(Category.class, id);
        
    }
	
    @Override
	public Category findCategoryEagerly(Long id) {
		if (id == null) return null;
                return (Category)sessionFactory.getCurrentSession()
                                               .createQuery("SELECT o FROM Category o LEFT OUTER JOIN FETCH o.products WHERE o.id = :id")
                                               .setParameter("id", id)
                                               .uniqueResult();
        //return entityManager.createQuery("SELECT o FROM Category o LEFT OUTER JOIN FETCH o.products WHERE o.id = (:id)", Category.class).setParameter("id", id).getSingleResult();
	}

    @Override
	public List<Category> findCategoryEntries(int firstResult, int maxResults) {
             return  sessionFactory.getCurrentSession().createQuery("SELECT o FROM Category o").setFirstResult(firstResult).setMaxResults(maxResults).list();
       // return entityManager.createQuery("SELECT o FROM Category o", Category.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	
    @Override
    public void persist(Category category) {
		//entityManager.persist(category);
                sessionFactory.getCurrentSession().save(category);
    }

	
    @Override
    public void remove(Category category) {
      /*  if (this.entityManager.contains(category)) {
            this.entityManager.remove(category);
        } else {
            category = findCategory(category.getId());
            this.entityManager.remove(category);
        }
        */
      
         Category attached = findCategory(category.getId());
        
        if(attached!=null){
            sessionFactory.getCurrentSession().delete(attached); 
        }
     
      
      
    }

	
    @Override
    public void flush() {
       // entityManager.flush();
          sessionFactory.getCurrentSession().flush();
    }

	
    @Override
    public void clear() {
       // entityManager.clear();
         sessionFactory.getCurrentSession().clear();
    }


    @Override
    public Category merge(Category category) {
       /* Category merged = entityManager.merge(category);
        entityManager.flush();
        return merged;
        */
        Category merged =(Category)sessionFactory.getCurrentSession().merge(category);
       sessionFactory.getCurrentSession().flush();
        return merged;
        
    }

}
