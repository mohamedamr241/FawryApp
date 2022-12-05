package javaApp;

public class DonationService extends Services {

	protected void setData()
	{
		paymentMethods.add("CreditCard");
		paymentMethods.add("Cash");
		serviceProviders.add("CancerHospital");
		serviceProviders.add("Schools");
		serviceProviders.add("NGOs");
		
	}
	public ServiceProviders createServiceProvider(int n) {
		ServiceProviders sp = null;
		if(n == 1) {
			providerHandler=new HospitalHandler();
			sp = new CancerHospital();
		}
		else if(n == 2)
			sp = new Schools();
		else if(n == 3)
			sp = new NGOs();
		
		return sp;

		
	}	
}
