package com.pavikumbhar.springmvc.store.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pavikumbhar.springmvc.store.model.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository

public class ProductDao {

	//@PersistenceContext
    //private EntityManager entityManager;
        
        
        @Autowired
      SessionFactory sessionFactory;
	
     
	public List<Product> findProductsByFeatured(Boolean featured) {
        if (featured == null) throw new IllegalArgumentException("The featured argument is required");
      /*  TypedQuery<Product> q = entityManager.createQuery("SELECT o FROM Product AS o WHERE o.featured = :featured", Product.class);
        q.setParameter("featured", featured);
        return q.getResultList();*/
      return sessionFactory.getCurrentSession()
                                               .createQuery("SELECT o FROM Product AS o WHERE o.featured = :featured")
                                               .setParameter("featured", featured)
                                               .list();
    }

	public long countProducts() {
       // return entityManager.createQuery("SELECT COUNT(o) FROM Product o", Long.class).getSingleResult();
        Long count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(o) FROM Product o").uniqueResult();
        return count;

    }

	public List<Product> findAllProducts() {
          return sessionFactory.getCurrentSession().createQuery("SELECT o FROM Product o").list();   
        //return entityManager.createQuery("SELECT o FROM Product o", Product.class).getResultList();
    }

	public Product findProduct(Long id) {
        if (id == null) return null;
        //return entityManager.find(Product.class, id);
         return (Product)sessionFactory.getCurrentSession().get(Product.class, id);
    }

	public List<Product> findProductEntries(int firstResult, int maxResults) {
        //return entityManager.createQuery("SELECT o FROM Product o", Product.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
         return  sessionFactory.getCurrentSession().createQuery("SELECT o FROM Product o").setFirstResult(firstResult).setMaxResults(maxResults).list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductEntries(Product product) {
		Query query = null;
		if (product.getCategory() != null && product.getCategory().getId() != 0) {
			//query = entityManager.createQuery("SELECT o FROM Product o WHERE o.name like :name and o.category = :category", Product.class);
                       query= sessionFactory.getCurrentSession().createQuery("SELECT o FROM Product o WHERE o.name like :name and o.category = :category");
			query.setParameter("name", "%" + product.getName() + "%");
	        query.setParameter("category", product.getCategory());
		}
		else {
			//query = entityManager.createQuery("SELECT o FROM Product o WHERE o.name like :name", Product.class);
                         query= sessionFactory.getCurrentSession().createQuery("SELECT o FROM Product o WHERE o.name like :name");
	        query.setParameter("name", "%" + product.getName() + "%");
		}
        //return query.getResultList();
        return query.list();
    }

	
	public void persist(Product product) {
       // this.entityManager.persist(product);
        sessionFactory.getCurrentSession().save(product);
    }

	public void remove(Product product) {
       /* if (this.entityManager.contains(product)) {
            this.entityManager.remove(product);
        } else {
            Product attached = findProduct(product.getId());
            this.entityManager.remove(attached);
        } 
        */
       
       Product attached = findProduct(product.getId());
        
        if(attached!=null){
            sessionFactory.getCurrentSession().delete(attached); 
        }
    }

	public void flush() {
        //this.entityManager.flush();
         sessionFactory.getCurrentSession().flush();
    }

	public void clear() {
      //  this.entityManager.clear();
       sessionFactory.getCurrentSession().clear();
    }

	public Product merge(Product product) {
        /*Product merged = this.entityManager.merge(product);
        this.entityManager.flush();
        return merged;
       */
        
        Product merged =(Product)sessionFactory.getCurrentSession().merge(product);
       sessionFactory.getCurrentSession().flush();
        return merged;

    }



}
