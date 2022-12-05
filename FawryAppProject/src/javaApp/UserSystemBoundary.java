package javaApp;


import java.util.*;

public class UserSystemBoundary {
	ServiceProviders servprovider;
	Scanner scan = new Scanner(System.in);
	String UserEmail;
	int counter=0;
	public UserSystemBoundary(String email) {
		UserEmail=email;
		counter=Account.userTransactionNumber.get(UserEmail);
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
			System.out.println("[6] check notifications:");
			System.out.println("[7] exit:");
			int option = scan.nextInt();
			switch (option) {
			case 1:
				System.out.println("MobileRecharge");
				System.out.println("InternetPayment");
				System.out.println("Landline");
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
				for(Map.Entry<String, Integer> entry : SpecificDiscount.serviceDiscount.entrySet())
				{
					System.out.print("Service " + entry.getKey());
					System.out.println(" has discount " + entry.getValue() + " $");
				}
				break;
			case 5:
				chargeWallet();
				break;
			case 6:
				for (Map.Entry<String, User> entry : Account.users.entrySet())
				{
					if(entry.getKey().equals(UserEmail))
					{
						entry.getValue().notifications.size();
						for(int i=0;i<entry.getValue().notifications.size();i++) {
							System.out.println(entry.getValue().notifications.get(i));
						}
					}
				}
				break;
			case 7:
				cont=false;
				Account.userTransactionNumber.put(UserEmail,counter);
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
        String serve = scan.next();
//        serve = scan.nextLine();
//        serve +=scan.nextLine();
		if(serve.equals("MobileRecharge")) {
			
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
					if(counter == 0) {
						payMethod = new OverallDiscount(payMethod);
					}
					
					boolean serviceDiscount = SpecificDiscount.searchService(serve);
					if(serviceDiscount)
						payMethod = new SpecificDiscount(payMethod);							
					mobile.setPayMethod(payMethod);	
					mobile.performPayMethod(price);
					
					System.out.println("Enter the following: ");
					CreditCard.creditCardForm();
					String creditCardNum = scan.next(), CCN = scan.next();
					
					System.out.println("payment with credit card is done successfully");
					counter++;
					
				}
				else if(payMthodNum == 2)//cash
				{
					Payment payMethod = new Cash();
					if(counter == 0) {
						payMethod = new OverallDiscount(payMethod);
					}
					
					boolean serviceDiscount = SpecificDiscount.searchService(serve);
					if(serviceDiscount)
						payMethod = new SpecificDiscount(payMethod);							
					mobile.setPayMethod(payMethod);	
					mobile.performPayMethod(price);
					System.out.println("payment with cash is done successfully");
					counter++;
				}
				else if (payMthodNum ==3){ //wallet
					Wallet userWallet = Wallet.getUserWallet(UserEmail);
					if(counter==0) {
						Payment discount = new OverallDiscount(userWallet);
						boolean serviceDiscount = SpecificDiscount.searchService(serve);
						if(serviceDiscount)
						{
							discount = new SpecificDiscount(discount);
						}
						mobile.setPayMethod(discount);
					}
					else {
						boolean serviceDiscount = SpecificDiscount.searchService(serve);
						if(serviceDiscount)
						{
							Payment discount = new SpecificDiscount(userWallet);
							mobile.setPayMethod(discount);

						}
						else {
							mobile.setPayMethod(userWallet);								
						}
					}
					if(userWallet.getBalance()>=price) {
						mobile.performPayMethod(price);
						System.out.println("payment with wallet is done successfully");
						System.out.println(userWallet.getBalance());
						counter++;
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
		
		else if(serve.equals("Landline"))
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
			System.out.println("Choose payment method by number");
			ArrayList<String> paymentMethods = landline.displayPayMethods();
			for(int i = 1; i <= (int)paymentMethods.size(); i++)
			{
				System.out.println("[" + i + "] " + paymentMethods.get(i - 1));
			}
			int payMthodNum = scan.nextInt();
			if(payMthodNum == 1) //credit card
			{
				Payment payMethod = new CreditCard();
				if(counter == 0) {
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				landline.setPayMethod(payMethod);	
				landline.performPayMethod(price);
				System.out.println("Enter the following: ");
				CreditCard.creditCardForm();
				String creditCardNum = scan.next(), CCN = scan.next();
				System.out.println("payment with credit card is done successfully");
				counter++;
			}
			else if(payMthodNum == 2)//cash
			{
				Payment payMethod = new Cash();
				if(counter == 0) {
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				landline.setPayMethod(payMethod);	
				landline.performPayMethod(price);
				System.out.println("payment with cash is done successfully");
				counter++;
			}
			
		}
		else if(serve.equals("InternetPayment"))
		{
			Services Internet = new InternetService();
			Internet.displayProviders();
			int providerNum=scan.nextInt();
			servprovider=Internet.orderServiceProvider(providerNum);	
			
		
			providerForm = servprovider.getForm();
			for(String field : providerForm)
			{
				System.out.println("Enter " + field);
				String ans = scan.next();
				formAns.add(ans);

			}
			System.out.println("Choose payment method by number");
			ArrayList<String> paymentMethods = Internet.displayPayMethods();
			for(int i = 1; i <= (int)paymentMethods.size(); i++)
			{
				System.out.println("[" + i + "] " + paymentMethods.get(i - 1));
			}
			int payMthodNum = scan.nextInt();
			if(payMthodNum == 1) //credit card
			{
				Payment payMethod = new CreditCard();
				if(counter == 0) {
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				Internet.setPayMethod(payMethod);	
				Internet.performPayMethod(price);
				System.out.println("Enter the following: ");
				CreditCard.creditCardForm();
				String creditCardNum = scan.next(), CCN = scan.next();
				System.out.println("payment with credit card is done successfully");
				counter++;

			}
			else if(payMthodNum == 2)//cash
			{
				Payment payMethod = new Cash();
				if(counter == 0) {
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				Internet.setPayMethod(payMethod);	
				Internet.performPayMethod(price);
				System.out.println("payment with cash is done successfully");
				counter++;
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
			System.out.println("Choose payment method by number");
			ArrayList<String> paymentMethods = donation.displayPayMethods();
			for(int i = 1; i <= (int)paymentMethods.size(); i++)
			{
				System.out.println("[" + i + "] " + paymentMethods.get(i - 1));
			}
			int payMthodNum = scan.nextInt();
			if(payMthodNum == 1) //credit card
			{
				Payment payMethod = new CreditCard();
				if(counter == 0) {
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				donation.setPayMethod(payMethod);	
				donation.performPayMethod(price);
				System.out.println("Enter the following: ");
				((CreditCard) payMethod).creditCardForm();
				String creditCardNum = scan.next(), CCN = scan.next();
				System.out.println("payment with credit card is done successfully");
				counter++;

			}
			else if(payMthodNum == 2)//cash
			{
				Payment payMethod = new Cash();
				if(counter == 0) {
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				donation.setPayMethod(payMethod);	
				donation.performPayMethod(price);
				System.out.println("payment with cash is done successfully");
				counter++;
			}
		}
	}

}

