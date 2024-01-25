package Eventmanagement.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.Query;
import javax.persistence.Persistence;
import Eventmanagement.dao.AdminDao;
import Eventmanagement.dao.ClientDao;
import Eventmanagement.dao.ClientEventDao;
import Eventmanagement.dao.ClientServiceDao;
import Eventmanagement.dao.ServiceDao;
import Eventmanagement.dto.Admin;
import Eventmanagement.dto.Client;
import Eventmanagement.dto.ClientEvent;
import Eventmanagement.dto.ClientService;
import Eventmanagement.dto.EventType;
import Eventmanagement.dto.Service;

public class Eventmanagement {
    AdminDao adao = new AdminDao();
    ServiceDao sdao = new ServiceDao();
    ClientDao cdao = new ClientDao();
    ClientEventDao ceDao = new ClientEventDao();
    ClientServiceDao csDao = new ClientServiceDao();
    
    public static void main(String[] args) {
		Eventmanagement em = new Eventmanagement();
		System.out.println(em.creatClientEvent());
	}
    public Admin saveAdmin() {
    	Admin admin = new Admin();
    	Scanner s = new Scanner(System.in);
    	System.out.println("enter admin name");
    	admin.setAdminName(s.next());
    	System.out.println("enter admin mail");
    	admin.setAdminMail(s.next());
    	System.out.println("enter admin password");
    	admin.setAdminPassword(s.next());
    	System.out.println("enter admin contact number");
    	admin.setAdminContact(s.nextLong());
    	
    	return adao.saveAdmin(admin);
    }
    public Admin adminLogin(){
    	Scanner s = new Scanner(System.in);
    	System.out.println("enter admin email");
    	String adminMail = s.next();
    	System.out.println("Enter admin password");
    	String adminPassword = s.next();
    	Query query = Persistence.createEntityManagerFactory("amit").createEntityManager().createQuery(" select a from Admin a where a.adminMail = ?1");
    	query.setParameter(1, adminMail);
    	Admin exAdmin = (Admin)query.getSingleResult();
    	if(exAdmin !=null){
    		if(exAdmin.getAdminPassword().equals(adminPassword)) {
    			return exAdmin;
    			
    	}
    		return null;
    }
    	return null;
  }
       public Service saveService() {
    	   System.out.println("Enter the admin credentials to proceed");
    	   Admin exAdmin=adminLogin();
    	   if(exAdmin!=null) {
    		   Service service = new Service();
    		   Scanner s = new Scanner(System.in);
    		   System.out.println("enter service name");
    		   service.setServiceName(s.next());
    		   System.out.println("enter service cost per person");
    		   service.setServiceCostPerPerson(s.nextDouble());
    		   System.out.println("enter service cost per day");
    		   service.setServiceCostperDay(s.nextDouble());
    		   Service savedService = sdao.saveService(service);
    		   exAdmin.getServices().add(savedService);
    		   adao.updateAdmin(exAdmin, exAdmin.getAdminId());
    		   
    		   return service;
    	   }
    	   
		return null;
       }
      
       public List<Service> getALlServices(){
    	   System.out.println("Enter the admin credentials to proceed");
    	      Admin exAdmin = adminLogin();
    	      if(exAdmin!=null) {
    	    	  
    	    	  Query query = Persistence.createEntityManagerFactory("amit").createEntityManager().createQuery("select s from Service s ");
    	    	  List<Service> services = query.getResultList();
    	    	  return services;
    	    	  
    	   }
    	      
    	  return null;
       }
       public String  updateService() {
    	   Scanner sc = new Scanner(System.in);
    	   List<Service> services=getALlServices();
    	   for(Service s:services) {
    		   System.out.println("serviceId   "+"serviceName  "+"cost_per_person  "+"cost_per_day");
    		   System.out.println("  "+s.getServiceId()+"   "+s.getServiceName()+"  "+s.getServiceCostPerPerson()+"   "+s.getServiceCostperDay());
   		   
    	   }
    	   System.out.println("%%%%% Choose service id from above to update %%%%%%%");
    	   int id=sc.nextInt();
    	   Service tobeupdated = sdao.findService(id);
    	   System.out.println("enter updated cost per person");
    	   double costperPerson=sc.nextDouble();
    	   System.out.println("Enter updated cost per day");
    	   double costperday = sc.nextDouble();
    	   tobeupdated.setServiceCostperDay(costperday);
    	   tobeupdated.setServiceCostPerPerson(costperPerson);
    	   
    	   Service updated = sdao.updateService(id, tobeupdated);
    	   if(updated != null) {
    		   
    		   return "service update success";
    	   }
    	   return "invalid service id";
       }
       
       public Service deleteService() {
    	 Scanner sc = new Scanner(System.in);
    	   Admin exAdmin = adminLogin();
    	   if(exAdmin!=null) {
    	     	List<Service> services = exAdmin.getServices();
    	        System.out.println("%%%%%%%%%% choose service id from above to deletem%%%%%%%%%%");
    		   for(Service s : services) {
    			   
    			   System.out.println("serviceId  "+ " serviceName  "+"cost_per_person  "+"cost_per_day");
    			   System.out.println("  "+s.getServiceId()+"  "+s.getServiceName()+" "+s.getServiceCostperDay()+"  "+s.getServiceCostPerPerson());
    			   
    		   }
    		
    		   int id = sc.nextInt();
    		      
    		   List<Service> newList = new ArrayList<Service>();
    		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
    		   for(Service s : services 
    				   ) {
    			   if(s.getServiceId() != id) {
    				   newList.add(s);
    			   }
    		   }
    	   exAdmin.setServices(newList);
    	   adao.updateAdmin(exAdmin, exAdmin.getAdminId());
    	   sdao.deleteService(id);
    	   }
		return null;
       }
     public Client ClientSignup(){
       Client client = new Client();
       Scanner s = new Scanner(System.in);
         System.out.println("enter the client name");
         client.setClientname(s.next());
         System.out.println("enter the client email");
          client.setClientMail(s.next());
          System.out.println("enter the client contact");
          client.setContact(s.nextLong());
          System.out.println("enter the client password");
          client.setClientPassword(s.next());
          return cdao.saveClient(client);
   	}
     
    public Client clientLogin() {
    	Scanner  s = new Scanner(System.in);
    	System.out.println("enter the client name");
    	String clientEmail = s.next();
    	System.out.println("enter the client password");
    	String clientPassword = s.next();
    	Query query = Persistence.createEntityManagerFactory("amit").createEntityManager().createQuery("select c from Client c where c.clientMail=?1");
    	query.setParameter(1,clientEmail);
    	Client exClient = (Client)query.getSingleResult();
    	if(exClient !=null) {
    		if(exClient.getClientPassword().equals(clientPassword)){
    			return exClient;
    	}
    		return null;
     }
    	return null;	
    }
   public ClientEvent creatClientEvent() {
	   
	      Client exclient = clientLogin();
	      if(exclient != null) {
	    	  ClientEvent ce = new ClientEvent();
	    	  EventType[] et = EventType.values();
	    	  int i=1;
	    	  for(EventType eventType : et) {
	    		  System.out.println((i++)+ " "+ eventType);
	    	  }
	    	  Scanner sc = new Scanner(System.in);
	    	  int key =sc.nextInt();
	    	  switch(key) {
	    	  case 1:ce.setEventType(EventType.Marraige);break;
	    	  case 2:ce.setEventType(EventType.Engagement);break;
	    	  case 3:ce.setEventType(EventType.Birthday);break;
	    	  case 4:ce.setEventType(EventType.Anniversary);break;
	    	  case 5:ce.setEventType(EventType.Babyshower);break;
	    	  case 6:ce.setEventType(EventType.Reunions);break;
	    	  case 7:ce.setEventType(EventType.earingCeremony);break;
	    	  case 8:ce.setEventType(EventType.BachelorParty);break;
	    	   default:System.out.println("Invalid Input");break;
	    	  
	    	  }
	    	  System.out.println("Enter the  Number of people attending the event");
	    	  ce.setClientEventNoOfPeople(sc.nextInt());
	    	  System.out.println("enter the number of days for the event");
	    	  ce.setClientEventNoOfDays(sc.nextInt());
	    	  System.out.println("enter the location");
	    	  ce.setClientEventlocation(sc.next());
	    	  System.out.println("enter the start date for the Event ");
	    	  ce.setStartDate(LocalDate.parse(sc.next()));
	    	  
	    	  ClientEvent saveClientEvent=ceDao.saveClientEvent(ce);
	    	  exclient.getEvents().add(saveClientEvent);
	    	  cdao.updateClient(exclient.getClientId(), exclient);
	      }
		return null;
    }
   
    public void addEventServices() {
    	Client client = clientLogin();
       	Scanner sc = new Scanner(System.in);
    	if(client != null)
    	{
    		
    		for(ClientEvent event : client.getEvents()) {
    		System.out.println("Event Id  "+ " Event type  "+ "EventLocation");
    		System.out.println(event.getClientEventId() + " " + event.getEventType()+ " " + event.getClientEventlocation());
    		}
    		
    		System.out.println("enter the event id you want to add services ");
    		int eventid = sc.nextInt();
    		ClientEvent event = ceDao.findClientEvent(eventid);
    		if(event != null) {
    			Admin admin = adminLogin();
    			if(admin != null) {
    				System.out.println("enter the number of service you visit to add to the event");
    				int count = sc.nextInt();
    				while(count >0) {
    					for(Service cs : admin.getServices()) {
    						System.out.println(cs.getServiceId()+" " + cs.getServiceName());
    						
    					}
    				 System.out.println("enter the service id you want to choose");
    				 int serviceId = sc.nextInt();
    	            			 
    				 Service exService = sdao.findService(serviceId);
    				 ClientService cs = new ClientService();
      			     cs.setClientServiceCost(exService.getServiceCostperDay());
    				 cs.setClientServiceName(exService.getServiceName());
    				 cs.setClientservicenoOfDays(event.getClientEventNoOfDays());
    				 cs.setClientserviceCostPerPerson(exService.getServiceCostPerPerson());
    				 
    				 if(exService.getServiceCostPerPerson()==0) {
    					 cs.setClientServiceCost(exService.getServiceCostperDay()+event.getClientEventNoOfDays());
    				 }
    				 else {
    					 cs.setClientServiceCost(exService.getServiceCostperDay()+event.getClientEventNoOfDays()+exService.getServiceCostPerPerson()+event.getClientEventNoOfPeople());
    				 }
    				 event.setClientEventCost(event.getClientEventCost()+cs.getClientServiceCost());
    				 event.getClientServices().add(cs);
    				 ceDao.updateClientEvent(event,event.getClientEventId());
    				 count--;
    				 }
    			}
    		}
    	}
     }
    
  } 