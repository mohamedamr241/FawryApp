package javaApp;

public class LandlineService extends Services{

	
	@Override
	protected void setData() {
		paymentMethods.add("CreditCard");
		paymentMethods.add("Cash");
		serviceProviders.add("MonthlyReceipt");
		serviceProviders.add("QuarterReceipt");
		
	}

	@Override
	public ServiceProviders createServiceProvider(int n) {
		ServiceProviders sp = null;
		
		if(n == 1)
			sp = new MonthlyReceipt();
//		else if (n == 2)
//			sp = new MonthlyReceipt();
		
		return sp;
	}

//	@Override
//	public void displayPaymentForm() {
//		// TODO Auto-generated method stub
//		
//	}




	
	


}
