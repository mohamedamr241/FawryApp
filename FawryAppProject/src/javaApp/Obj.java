package javaApp;

public class Obj {

	public int transId;
	public double amount;
	public String serviceName;
	public String userEmail;
	public Obj(int transId)
	{
		this.transId = transId;
	}
	public Obj(int transId, double amount,String serviceName, String userEmail) {
		this.transId = transId;
		this.amount = amount;
		this.serviceName = serviceName;
		this.userEmail = userEmail;
	}
	
	public String toString() {
	    return "transaction No: " + transId + 
	           ", amount: " + amount +
	           ", service name: " + serviceName +
	           ", your email: " + userEmail;

	    
	}
}
