package javaApp;

public class Vodafone extends ServiceProviders{
	@Override
	protected void setDataForm() {
		serviceProviderForm.add("Mobile Number");
		serviceProviderForm.add("Amount");		
	}
}
