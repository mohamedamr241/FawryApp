package javaApp;


public class Etisalat extends ServiceProviders{

	@Override
	protected void setDataForm() {
		serviceProviderForm.add("Mobile Number");
		serviceProviderForm.add("Amount");		
	}
}
