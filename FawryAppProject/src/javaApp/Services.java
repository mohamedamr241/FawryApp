package javaApp;

import java.util.ArrayList;

public abstract class Services {
	public Handler providerHandler;
	Payment paymentMethod;
	ArrayList<String> serviceProviders = new ArrayList<String>();
	ArrayList<String> paymentMethods = new ArrayList<String>();
	public Services()
	{
		setData();
	}
	public abstract ServiceProviders createServiceProvider(int num);
	protected abstract void setData();
	public void displayProviders() {
		for(int i = 1; i <= (int)serviceProviders.size(); i++)
		{
			System.out.println("[" + i + "] " + serviceProviders.get(i - 1));
		}
	}
//	public abstract void displayPaymentForm();
	public ServiceProviders orderServiceProvider(int num) {
		ServiceProviders serv = createServiceProvider(num);
		return serv;
	}
}
