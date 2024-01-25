package Eventmanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import Eventmanagement.dto.ClientEvent;

public class ClientEventDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("amit");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et= em.getTransaction();

	public ClientEvent saveClientEvent(ClientEvent clientEvent) {
		et.begin();
		em.persist(clientEvent);
		et.commit();
		return clientEvent;
	}
	     public ClientEvent findClientEvent(int id) {
	    	 ClientEvent clientEvent = em.find(ClientEvent.class, id);
	    	 if(clientEvent!= null) {
	    		 return clientEvent;
	    	 }
	    	 return clientEvent;
	     }
	     public ClientEvent deleteClientEvent(int id) {
	    	 ClientEvent clientEvent = em.find(ClientEvent.class,id);
	    	 if(clientEvent != null) {
	    		 et.begin();
	    		 em.remove(clientEvent);
	    		 et.commit();
	    		 return clientEvent;
	    	 }
	    	 return null;
	     }
	     public ClientEvent updateClientEvent(ClientEvent clientEvent,int id) {
	    	 ClientEvent clientEvent1 = em.find(ClientEvent.class, id);
	    	 if(clientEvent1 != null) {
	    		 clientEvent1.setClientEventId(id);
	    		 et.begin();
	    		 em.merge(clientEvent);
	    		 et.commit();
	    		 return clientEvent;
	    	 }
	    	 return null;
	     }
}
