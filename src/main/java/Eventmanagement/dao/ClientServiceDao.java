package Eventmanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import Eventmanagement.dto.ClientService;

public class ClientServiceDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("amit");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et= em.getTransaction();

	public ClientService saveClientService(ClientService clientservice) {
		et.begin();
		em.persist(clientservice);
		et.commit();
		return clientservice;
	}
	     public ClientService findClientService(int id) {
	    	 ClientService clientservice = em.find(ClientService.class, id);
	    	 if(clientservice!= null) {
	    		 return clientservice;
	    	 }
	    	 return clientservice;
	     }
	     public ClientService deleteClientService(int id) {
	    	 ClientService clientservice = em.find(ClientService.class,id);
	    	 if(clientservice != null) {
	    		 et.begin();
	    		 em.remove(clientservice);
	    		 et.commit();
	    		 return clientservice;
	    	 }
	    	 return null;
	     }
	     public ClientService updateClientService(int id,ClientService clientservice) {
	    	 ClientService clientservice1 = em.find(ClientService.class, id);
	    	 if(clientservice1 != null) {
	    		 clientservice1.setClientServiceId(id);
	    		 et.begin();
	    		 em.merge(clientservice);
	    		 et.commit();
	    		 return clientservice;
	    	 }
	    	 return null;
	     }
}
