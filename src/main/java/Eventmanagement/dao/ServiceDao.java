package Eventmanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Eventmanagement.dto.Admin;
import Eventmanagement.dto.Service;

public class ServiceDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("amit");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et= em.getTransaction();

	public Service saveService(Service service) {
		et.begin();
		em.persist(service);
		et.commit();
		return service;
	}
	     public Service findService(int id) {
	    	 Service service = em.find(Service.class, id);
	    	 if(service!= null) {
	    		 return service;
	    	 }
	    	 return service;
	     }
	     public Service deleteService(int id) {
	    	 Service service = em.find(Service.class,id);
	    	 if(service != null) {
	    		 et.begin();
	    		 em.remove(service);
	    		 et.commit();
	    		 return service;
	    	 }
	    	 return null;
	     }
	     public Service updateService(int id,Service service) {
	    	Service service1 = em.find(Service.class, id);
	    	 if(service1 != null) {
	    		 service1.setServiceId(id);
	    		 et.begin();
	    		 em.merge(service);
	    		 et.commit();
	    		 return service;
	    	 }
	    	 
	    	 return null;
	     }
		
			
		}

