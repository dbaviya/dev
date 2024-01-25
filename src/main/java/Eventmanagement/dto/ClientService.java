package Eventmanagement.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class ClientService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientServiceId;
    private double clientServiceCost;
    private String clientServiceName;
    private int clientservicenoOfDays;
    private double clientserviceCostPerPerson;
	public int getClientServiceId() {
		return clientServiceId;
	}
	public void setClientServiceId(int clientServiceId) {
		this.clientServiceId = clientServiceId;
	}
	public double getClientServiceCost() {
		return clientServiceCost;
	}
	public void setClientServiceCost(double clientServiceCost) {
		this.clientServiceCost = clientServiceCost;
	}
	public String getClientServiceName() {
		return clientServiceName;
	}
	public void setClientServiceName(String clientServiceName) {
		this.clientServiceName = clientServiceName;
	}
	public int getClientservicenoOfDays() {
		return clientservicenoOfDays;
	}
	public void setClientservicenoOfDays(int clientservicenoOfDays) {
		this.clientservicenoOfDays = clientservicenoOfDays;
	}
	public double getClientserviceCostPerPerson() {
		return clientserviceCostPerPerson;
	}
	public void setClientserviceCostPerPerson(double clientserviceCostPerPerson) {
		this.clientserviceCostPerPerson = clientserviceCostPerPerson;
	}
	@Override
	public String toString() {
		return "ClientService [clientServiceId=" + clientServiceId + ", clientServiceCost=" + clientServiceCost
				+ ", clientServiceName=" + clientServiceName + ", clientservicenoOfDays=" + clientservicenoOfDays
				+ ", clientserviceCostPerPerson=" + clientserviceCostPerPerson + "]";
	}
    
    
}
