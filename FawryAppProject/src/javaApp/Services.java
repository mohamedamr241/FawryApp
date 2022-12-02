package javaApp;

public abstract class Services {
	public abstract FinancialServices createServiceProvider(String type);
	public abstract void pay();
	public abstract void displayPaymentForm();
	public abstract void displayserviceProvidersForm();
	public FinancialServices orderServiceProvider(String type) {
		FinancialServices serv = createServiceProvider(type);
		return serv;
	}
}
