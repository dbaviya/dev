package Eventmanagement.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    private String clientname;
    private long contact;
    private String clientMail;
    private String clientPassword;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ClientEvent> events;
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getClientMail() {
		return clientMail;
	}
	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	public List<ClientEvent> getEvents() {
		return events;
	}
	public void setEvents(List<ClientEvent> events) {
		this.events = events;
	}
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientname=" + clientname + ", contact=" + contact + ", clientMail="
				+ clientMail + ", clientPassword=" + clientPassword + ", events=" + events + "]";
	}
	
    		
    
}
