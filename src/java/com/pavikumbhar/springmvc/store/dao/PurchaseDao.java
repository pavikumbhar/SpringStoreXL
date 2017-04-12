package com.pavikumbhar.springmvc.store.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pavikumbhar.springmvc.store.model.Purchase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository

public class PurchaseDao {

	//@PersistenceContext
    //private EntityManager entityManager;
    
     @Autowired
      SessionFactory sessionFactory;
	
     
     

	public long countPurchases() {
     //   return entityManager.createQuery("SELECT COUNT(o) FROM Purchase o", Long.class).getSingleResult();
             Long count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(o) FROM Purchase o").uniqueResult();
        return count;

    }

	public List<Purchase> findAllPurchases() {
             return sessionFactory.getCurrentSession().createQuery("SELECT o FROM Purchase o").list(); 
        //return entityManager.createQuery("SELECT o FROM Purchase o", Purchase.class).getResultList();
    }

	public Purchase findPurchase(Long id) {
        if (id == null) return null;
       // return entityManager.find(Purchase.class, id);
        return (Purchase)sessionFactory.getCurrentSession().get(Purchase.class, id);
    }

	public List<Purchase> findPurchaseEntries(int firstResult, int maxResults) {
       // return entityManager.createQuery("SELECT o FROM Purchase o", Purchase.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
     return  sessionFactory.getCurrentSession().createQuery("SELECT o FROM Purchase o").setFirstResult(firstResult).setMaxResults(maxResults).list();
     }

	@Transactional
    public void persist(Purchase purchase) {
       // entityManager.persist(purchase);
         sessionFactory.getCurrentSession().save(purchase);
   
    }

	@Transactional
    public void remove(Purchase purchase) {
       /* if (entityManager.contains(purchase)) {
            entityManager.remove(purchase);
        } else {
            Purchase attached = findPurchase(purchase.getId());
            entityManager.remove(attached);
        }
       */
       
        Purchase attached = findPurchase(purchase.getId());
        
        if(attached!=null){
            sessionFactory.getCurrentSession().delete(attached); 
        }
    }

	@Transactional
    public void flush() {
       // entityManager.flush();
       sessionFactory.getCurrentSession().flush();
    }

	@Transactional
    public void clear() {
        //entityManager.clear();
        sessionFactory.getCurrentSession().clear();
    }

	@Transactional
    public Purchase merge(Purchase purchase) {
        /*Purchase merged = entityManager.merge(purchase);
        entityManager.flush();
        return merged;
        */
        
         Purchase merged =(Purchase)sessionFactory.getCurrentSession().merge(purchase);
       sessionFactory.getCurrentSession().flush();
        return merged;
    }


}
