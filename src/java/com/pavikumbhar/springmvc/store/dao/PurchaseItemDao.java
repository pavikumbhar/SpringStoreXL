package com.pavikumbhar.springmvc.store.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pavikumbhar.springmvc.store.model.PurchaseItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class PurchaseItemDao {

 // @PersistenceContext
   // private EntityManager entityManager;
    
       @Autowired
      SessionFactory sessionFactory;
	
     
     

	public long countPurchaseItems() {
       // return entityManager.createQuery("SELECT COUNT(o) FROM PurchaseItem o", Long.class).getSingleResult();
        Long count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(o) FROM PurchaseItem o").uniqueResult();
        return count;

    }

	public List<PurchaseItem> findAllPurchaseItems() {
       // return entityManager.createQuery("SELECT o FROM PurchaseItem o", PurchaseItem.class).getResultList();
       return sessionFactory.getCurrentSession().createQuery("SELECT o FROM PurchaseItem o").list(); 
    }

	public PurchaseItem findPurchaseItem(Long id) {
        if (id == null) return null;
       // return entityManager.find(PurchaseItem.class, id);
         return (PurchaseItem)sessionFactory.getCurrentSession().get(PurchaseItem.class, id);
    }

	public List<PurchaseItem> findPurchaseItemEntries(int firstResult, int maxResults) {
        //return entityManager.createQuery("SELECT o FROM PurchaseItem o", PurchaseItem.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        return  sessionFactory.getCurrentSession().createQuery("SELECT o FROM PurchaseItem o").setFirstResult(firstResult).setMaxResults(maxResults).list();
    }

	@Transactional
    public void persist(PurchaseItem purchaseItem) {
       // entityManager.persist(purchaseItem);
         sessionFactory.getCurrentSession().save(purchaseItem);
    }

	@Transactional
    public void remove(PurchaseItem purchaseItem) {
      /*  if (entityManager.contains(purchaseItem)) {
            entityManager.remove(purchaseItem);
        } else {
            PurchaseItem attached = findPurchaseItem(purchaseItem.getId());
            entityManager.remove(attached);
        }
       */
      
       PurchaseItem attached = findPurchaseItem(purchaseItem.getId());
        
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
    public PurchaseItem merge(PurchaseItem purchaseItem) {
        /*PurchaseItem merged = entityManager.merge(purchaseItem);
        entityManager.flush();
        return merged;
       */ 
         PurchaseItem merged =(PurchaseItem)sessionFactory.getCurrentSession().merge(purchaseItem);
       sessionFactory.getCurrentSession().flush();
        return merged;
    }


}
