package javaApp;

import java.util.ArrayList;
import java.util.Scanner;

public class UserSystemBoundary {
	ServiceProviders servprovider;
	public void Display()
	{
		Scanner scan = new Scanner(System.in);
		ArrayList<String> providerForm = new ArrayList<String>(); //to retrieve fields of the form
		ArrayList<String> formAns = new ArrayList<String>(); //to store user's answers to the form
		System.out.println("[1] Search for service by name:");
		System.out.println("[2] Refund:");
		System.out.println("[3] Check Discount:");		
		int option = scan.nextInt();
		int price = 0;
		switch (option) {
		case 1:
			System.out.println("Mobile recharge service");
			System.out.println("Internet payment service");
			System.out.println("Landline service");
			System.out.println("Donations");
	        String serve = " ";
	        serve = scan.nextLine();
	        serve +=scan.nextLine();
			if(serve.equals("Mobile recharge service")) {
				
				Services mobile = new MobileService();
				mobile.displayProviders();
				int providerNum=scan.nextInt();
				servprovider=mobile.orderServiceProvider(providerNum);	
				
			
				providerForm = servprovider.getForm();
				for(String field : providerForm)
				{
					System.out.println("Enter " + field);
					String ans = scan.next();
					formAns.add(ans);
					if(field == "Amount")
						price = Integer.valueOf(ans);

				}
				boolean check =mobile.providerHandler.execute(formAns);
				if(check)
				{
					System.out.println("choose payment method by number");
					ArrayList<String> paymentMethods = mobile.displayPayMethods();
					for(int i = 1; i <= (int)paymentMethods.size(); i++)
					{
						System.out.println("[" + i + "] " + paymentMethods.get(i - 1));
					}
					int paymthodNum = scan.nextInt();
					if(paymthodNum == 1)
					{
						Payment payMethod = new CreditCard();
						mobile.setPayMethod(payMethod);
						mobile.performPayMethod(price);
					}
					else if(paymthodNum == 2)
					{
						Payment payMethod = new Cash();
						mobile.setPayMethod(payMethod);
						mobile.performPayMethod(price);
					}
						
					
				}
				
				
				
			}
			
			else if(serve.equals("Landline service"))
			{
				Services landline = new LandlineService();
				landline.displayProviders();
				int providerNum=scan.nextInt();
				servprovider=landline.orderServiceProvider(providerNum);	
				
			
				providerForm = servprovider.getForm();
				for(String field : providerForm)
				{
					System.out.println("Enter " + field);
					String ans = scan.next();
					formAns.add(ans);

				}
				
			}
			else if(serve.equals("Internet payment service"))
			{
				Services landline = new InternetService();
				landline.displayProviders();
				int providerNum=scan.nextInt();
				servprovider=landline.orderServiceProvider(providerNum);	
				
			
				providerForm = servprovider.getForm();
				for(String field : providerForm)
				{
					System.out.println("Enter " + field);
					String ans = scan.next();
					formAns.add(ans);

				}
				
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



/*
 * System.out.println("[1] Show list of services:");
		System.out.println("[2] Search for service by name:");
		System.out.println("[3] Request refund:");
		System.out.println("[4] Check discount:");		
		int option = scan.nextInt();
		switch (option) {
		case 1:
			System.out.println("Mobile recharge service");
			System.out.println("Internet payment service");
			System.out.println("Landline service");
			System.out.println("Donations");
			break;

 */
