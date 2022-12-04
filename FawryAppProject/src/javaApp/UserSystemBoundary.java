package javaApp;

import java.util.ArrayList;
import java.util.Scanner;

public class UserSystemBoundary {
	ServiceProviders servprovider;
	Scanner scan = new Scanner(System.in);
	String UserEmail;
	public UserSystemBoundary(String email) {
		UserEmail=email;
	}
	public void Display()
	{
		boolean cont=true;
		while(cont) {
			Scanner scan = new Scanner(System.in);
			System.out.println("[1] Show list of services:");
			System.out.println("[2] Search for service by name:");
			System.out.println("[3] Request refund:");
			System.out.println("[4] Check discount:");
			System.out.println("[5] charge wallet:");
			System.out.println("[6] exit:");
			int option = scan.nextInt();
			switch (option) {
			case 1:
				System.out.println("Mobile recharge service");
				System.out.println("Internet payment service");
				System.out.println("Landline service");
				System.out.println("Donations");
				break;
				
			case 2:
				search();
				// map of list refund <string , int > -> <user email,amount of refund>
				break;
			case 3:
				System.out.println("First-time Transaction User Discount Save 10% On All Services  ");	
				//hayro7 ytcheck fe el map ely mawgoda discount el service de 3aliha kam fe el mia
				// display specific discount
				break;
			case 4:	
				//here
				break;
			case 5:
				chargeWallet();
				break;
			case 6:
				cont=false;
				break;
			
			
			}
		}
		
	}
	public void chargeWallet() {
		
		CreditCard creditcard = new CreditCard();
		System.out.println("Enter the following: ");
		CreditCard.creditCardForm();
		String creditCardNum = scan.next(), exDate = scan.next();
		Wallet userWallet = Wallet.getUserWallet(UserEmail);
		System.out.println("Enter the updated balance: ");
		double balance =scan.nextDouble();
		userWallet.chargeViaCreditCard(balance);
		
	}
	public void search() {
		double price = 0;
		ArrayList<String> providerForm = new ArrayList<String>(); //to retrieve fields of the form
		ArrayList<String> formAns = new ArrayList<String>(); //to store user's answers to the form
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
					price = Double.valueOf(ans);

			}
			boolean check =mobile.providerHandler.execute(formAns);
			if(check)
			{
				System.out.println("Choose payment method by number");
				ArrayList<String> paymentMethods = mobile.displayPayMethods();
				for(int i = 1; i <= (int)paymentMethods.size(); i++)
				{
					System.out.println("[" + i + "] " + paymentMethods.get(i - 1));
				}
				int payMthodNum = scan.nextInt();
				if(payMthodNum == 1) //credit card
				{
					Payment payMethod = new CreditCard();
					mobile.setPayMethod(payMethod);
					System.out.println("Enter the following: ");
					((CreditCard) payMethod).creditCardForm();
					String creditCardNum = scan.next(), CCN = scan.next();
					mobile.performPayMethod(price);

				}
				else if(payMthodNum == 2)//cash
				{
					Payment payMethod = new Cash();
					mobile.setPayMethod(payMethod);
					mobile.performPayMethod(price);
				}
				else if (payMthodNum ==3){ //wallet
					Wallet userWallet = Wallet.getUserWallet(UserEmail);
					mobile.setPayMethod(userWallet);
					if(userWallet.getBalance()>=price) {
						mobile.performPayMethod(price);
						check=false;
					}
					else {
						System.out.println("Your wallet balance is not enough");
						
					}
				}
				
				
			}
			else {
				System.out.println("Fields of the form is incorrect");
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
		
		else if(serve.equals("Donations"))
		{
			Services donation = new DonationService();
			donation.displayProviders();
			int providerNum=scan.nextInt();
			servprovider=donation.orderServiceProvider(providerNum);	
			
		
			providerForm = servprovider.getForm();
			for(String field : providerForm)
			{
				System.out.println("Enter " + field);
				String ans = scan.next();
				formAns.add(ans);

			}
		}
		//map of services -> array of service provider
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
