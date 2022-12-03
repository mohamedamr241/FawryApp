package javaApp;

public abstract class Services {
	public Handler providerHandler;
	public abstract ServiceProviders createServiceProvider(String type);
	public abstract void pay();
	public abstract void displayPaymentForm();
	public abstract void displayserviceProvidersForm();
	public ServiceProviders orderServiceProvider(String type) {
		ServiceProviders serv = createServiceProvider(type);
		return serv;
	}
}
