package javaApp;

import java.util.Scanner;

public class UserSystemBoundary {
	FinancialServices servprovider;
	public void Display()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("[1] Search for service:");
		System.out.println("[2] Refund:");
		System.out.println("[3] Check Specific Discount:");		
		int option = scan.nextInt();
		switch (option) {
		case 1:
			System.out.print("MobileService, Landline service, Internet service, Donations");
			System.out.println();
			System.out.println("Enter service name: ");
			String serve=scan.next();
			if(serve.equals("MobileService")) {
				Services mobile = new MobileService();
				mobile.displayserviceProvidersForm();
				String servePro=scan.next();
				servprovider=mobile.orderServiceProvider(servePro);	
				servprovider.here();
				mobile.displayPaymentForm();
			}
			//map of services -> array of service provider
		
			break;
			
		case 2:
			// map of list refund <string , int > -> <user email,amount of refund>
			break;
		case 3:
			System.out.println("First-time Transaction User Discount Save 10% On All Services  ");	
			//hayro7 ytcheck fe el map ely mawgoda discount el service de 3aliha kam fe el mia
			// display specific discount
		}
		
	}
	public void search(String type) {
		
	}

}
