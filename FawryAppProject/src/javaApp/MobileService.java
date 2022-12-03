package javaApp;


public class MobileService extends Services{
	
	protected void setData()
	{
		paymentMethods.add("[1]CreditCard");
		paymentMethods.add("[2]Cash");
		serviceProviders.add("We");
		serviceProviders.add("Etisalat");
		serviceProviders.add("Orange");
		serviceProviders.add("Vodafone");
	}

//	public void displayPaymentForm() {
//		for(int i=0;i<paymentMethodsName.size();i++) {
//			System.out.println(paymentMethodsName.get(i));
//		}
//		int c=scan.nextInt();
//		if(c==1) {
//			paymentMethod = new CreditCard();
//			paymentMethod.pay(30);
//		}
//		else if(c==2) {
//			paymentMethod = new Cash();
//			paymentMethod.pay(30);
//		}
//	}

	public ServiceProviders createServiceProvider(int n) {
		ServiceProviders sp = null;
		if(n == 1) {
			providerHandler=new WeHandler();
			sp = new We();
		}
		else if(n == 2) {
			providerHandler=new EtisalatHnadler();
			sp = new Etisalat();
		}
		else if(n == 3) {
//			providerHandler=new OrangeHnadler();
			sp = new Orange();
		}
//		else if(n == 4) {
////			providerHandler=new VodafoneHnadler();
//			sp = new Vodafone();
//		}
		return sp;

		
	}


}
