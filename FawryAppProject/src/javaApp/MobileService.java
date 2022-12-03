package javaApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MobileService extends Services{
	Scanner scan = new Scanner(System.in);
	ArrayList<String> paymentMethodsName = new ArrayList<String>();
	ArrayList<String> serviceProvidersName = new ArrayList<String>();
	Payment creditcard=new CreditCard();
	Payment cash=new Cash();
	public MobileService() {
		paymentMethodsName.add("[1]CreditCard");
		paymentMethodsName.add("[2]Cash");
		serviceProvidersName.add("we");
		serviceProvidersName.add("etisalat");
	}
	public void displayPaymentForm() {
		for(int i=0;i<paymentMethodsName.size();i++) {
			System.out.println(paymentMethodsName.get(i));
		}
		int c=scan.nextInt();
		if(c==1) {
			creditcard.pay(30);
		}
		else if(c==2) {
			cash.pay(30);
		}
	}
	public void displayserviceProvidersForm() {
		for(int i=0;i<serviceProvidersName.size();i++) {
			System.out.println(serviceProvidersName.get(i));
		}
	}
	public void pay() {
		System.out.print("pay");
	}
	public ServiceProviders createServiceProvider(String type) {
		if(type.equals("we")) {
			providerHandler=new WeHandler();
			return new We();
		}
		if(type.equals("etisalat")) {
			providerHandler=new EtisalatHnadler();
			return new Etisalat();
		}
		else{
			return null;
		}
		
	} 
}
