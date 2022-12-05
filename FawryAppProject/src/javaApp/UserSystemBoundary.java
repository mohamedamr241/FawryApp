package javaApp;


import java.util.*;

public class UserSystemBoundary {
	ServiceProviders servprovider;
	Scanner scan = new Scanner(System.in);
	String UserEmail;
	int transactionCounter=0;
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
				System.out.println("Enter your transaction ID");
				int transId = scan.nextInt();
				System.out.println("Enter the amount to be refunded");
				double amout = scan.nextDouble();
				System.out.println("Enter service name");
				String serviceName = scan.next();
				User.requestRefund(transId, amout, serviceName);
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
					if(transactionCounter == 0) {
						payMethod = new OverallDiscount(payMethod);
					}
					
					boolean serviceDiscount = SpecificDiscount.searchService(serve);
					if(serviceDiscount)
						payMethod = new SpecificDiscount(payMethod);							
					mobile.setPayMethod(payMethod);	
					mobile.performPayMethod(price);
					
//					System.out.println("Enter the following: ");
//					((CreditCard) payMethod).creditCardForm();
//					String creditCardNum = scan.next(), CCN = scan.next();
					
					System.out.println("payment with credit card is done successfully");
					
				}
				else if(payMthodNum == 2)//cash
				{
					Payment payMethod = new Cash();
					if(transactionCounter==0) {
						Payment discount = new OverallDiscount(payMethod);
						mobile.setPayMethod(discount);						
					}
					else {
						mobile.setPayMethod(payMethod);	
					}
					mobile.performPayMethod(price);
					System.out.println("payment with cash is done successfully");
				}
				else if (payMthodNum ==3){ //wallet
					Wallet userWallet = Wallet.getUserWallet(UserEmail);
					if(transactionCounter==0) {
						Payment discount = new OverallDiscount(userWallet);
						mobile.setPayMethod(discount);						
					}
					else {
						mobile.setPayMethod(userWallet);	
					}
					if(userWallet.getBalance()>=price) {
						mobile.performPayMethod(price);
						System.out.println("payment with wallet is done successfully");
						System.out.println(userWallet.getBalance());
						check=false;
					}
					else {
						System.out.println("Your wallet balance is not enough");
						
					}
				}
				
				transactionCounter++;
				Transactions.addTransaction(transactionCounter, price, serve);//UserEmail
				System.out.println("Your transaction id is " + transactionCounter + "(must be known so you can request rufund)");

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
				landline.setPayMethod(payMethod);
				System.out.println("Enter the following: ");
				((CreditCard) payMethod).creditCardForm();
				String creditCardNum = scan.next(), CCN = scan.next();
				landline.performPayMethod(price);

			}
			else if(payMthodNum == 2)//cash
			{
				Payment payMethod = new Cash();
				landline.setPayMethod(payMethod);
				landline.performPayMethod(price);
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
				landline.setPayMethod(payMethod);
				System.out.println("Enter the following: ");
				((CreditCard) payMethod).creditCardForm();
				String creditCardNum = scan.next(), CCN = scan.next();
				landline.performPayMethod(price);

			}
			else if(payMthodNum == 2)//cash
			{
				Payment payMethod = new Cash();
				landline.setPayMethod(payMethod);
				landline.performPayMethod(price);
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
				donation.setPayMethod(payMethod);
				System.out.println("Enter the following: ");
				((CreditCard) payMethod).creditCardForm();
				String creditCardNum = scan.next(), CCN = scan.next();
				donation.performPayMethod(price);

			}
			else if(payMthodNum == 2)//cash
			{
				Payment payMethod = new Cash();
				donation.setPayMethod(payMethod);
				donation.performPayMethod(price);
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
