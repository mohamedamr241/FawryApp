package javaApp;

import java.util.ArrayList;

public class Etisalat implements ServiceProviders{
	public Etisalat() {
		serviceProviderForm.add("Enter mobile Number: ");
		serviceProviderForm.add("Enter amount: ");
	}
	public ArrayList<String> getForm() {
		for(int i=0;i<serviceProviderForm.size();i++) {
			System.out.println(serviceProviderForm.get(i));
		}
		String number=scan.next();
		String amount=scan.next();
		ArrayList<String> providerForm = new ArrayList<String>();
		providerForm.add(number);
		providerForm.add(amount);
		return providerForm;
	}
}
