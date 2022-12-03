package javaApp;

public class DonationService extends Services {

	protected void setData()
	{
		paymentMethods.add("[1]CreditCard");
		paymentMethods.add("[2]Cash");
		//paymentMethods.add("[3]Wallet");
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
		else if(n == 2) {
		//	providerHandler=new SchoolsHanadler();
			sp = new Schools();
		}
		else if(n == 3) {
			//providerHandler=new NGOsHandler();
			sp = new NGOs();
		}
		
		return sp;

		
	}	
}
