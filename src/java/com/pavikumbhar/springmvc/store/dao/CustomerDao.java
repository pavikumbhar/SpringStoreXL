package com.pavikumbhar.springmvc.store.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pavikumbhar.springmvc.store.model.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author pavikumbhar
 */
@Repository
public class CustomerDao {

	//@PersistenceContext
        //private EntityManager entityManager;
        
        @Autowired
      SessionFactory sessionFactory;
	
     
	public long countCustomers() {
       
        Long count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(o) FROM Customer o").uniqueResult();
        return count;
        //return entityManager.createQuery("SELECT COUNT(o) FROM Customer o", Long.class).getSingleResult();
    }

	public List<Customer> findAllCustomers() {
           return sessionFactory.getCurrentSession().createQuery("SELECT o FROM Customer o").list();
        //return entityManager.createQuery("SELECT o FROM Customer o", Customer.class).getResultList();
    }

	public Customer findCustomer(Long id) {
        if (id == null) return null;
        return (Customer)sessionFactory.getCurrentSession().get(Customer.class, id);
        //return entityManager.find(Customer.class, id);
    }

	public List<Customer> findCustomerEntries(int firstResult, int maxResults) {
          return  sessionFactory.getCurrentSession().createQuery("SELECT o FROM Customer o").setFirstResult(firstResult).setMaxResults(maxResults).list();
        //return entityManager.createQuery("SELECT o FROM Customer o", Customer.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist(Customer customer) {
        //this.entityManager.persist(customer);
        sessionFactory.getCurrentSession().save(customer);
        
    }

	@Transactional
    public void remove(Customer customer) {
       /* if (this.entityManager.contains(customer)) {
            this.entityManager.remove(customer);
        } else {
            Customer attached = findCustomer(customer.getId());
            this.entityManager.remove(attached);
            
            
        }*/
       
        Customer attached = findCustomer(customer.getId());
        
        if(attached!=null){
            sessionFactory.getCurrentSession().delete(attached); 
        }
       
    }

	@Transactional
    public void flush() {
      //  this.entityManager.flush();
        sessionFactory.getCurrentSession().flush();
    }

	@Transactional
    public void clear() {
        //this.entityManager.clear();
        sessionFactory.getCurrentSession().clear();
    }

	@Transactional
    public Customer merge(Customer customer) {
       /* Customer merged = entityManager.merge(customer);
        this.entityManager.flush();
          */
       Customer merged =(Customer)sessionFactory.getCurrentSession().merge(customer);
       sessionFactory.getCurrentSession().flush();
        return merged;
    }
	/*
    public TypedQuery<Customer> findCustomersByNameEquals(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        TypedQuery<Customer> q = entityManager.createQuery("SELECT o FROM Customer AS o WHERE o.name = :name", Customer.class);
        q.setParameter("name", name);
        return q;
    }*/
    
    public Customer findCustomersByNameEquals(String name){
    
    return (Customer)sessionFactory.getCurrentSession()
                                   .createQuery("SELECT o FROM Customer AS o WHERE o.name = :name")
                                   . setParameter("name", name).uniqueResult();
    }


}
