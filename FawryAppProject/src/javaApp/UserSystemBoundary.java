package javaApp;


import java.util.*;

public class UserSystemBoundary {
	ServiceProviders servprovider;
	Scanner scan = new Scanner(System.in);
	String UserEmail;
	int transactionCounter = 0;
	static int counter = 0;
	double disPrice;
	public UserSystemBoundary(String email) {
		UserEmail=email;
		transactionCounter = Transactions.userTransactionNumber.get(UserEmail);
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
				break;
			case 3:
				refund();
				break;
			case 4:	
				discount();
				break;
			case 5:
				chargeWallet();
				break;
			case 6:
				discount();
				break;
			case 7:
				Transactions.userTransactionNumber.put(UserEmail,transactionCounter);
				cont=false;
				break;
			}
		}
		
	}
	public void notification() {
		for (Map.Entry<String, User> entry : Account.users.entrySet())
		{
			if(entry.getKey().equals(UserEmail))
			{
				for(int i=0;i<entry.getValue().notifications.size();i++) {
					System.out.println(entry.getValue().notifications.get(i));
				}
			}
		}
	}
	public void discount() {
		for(Map.Entry<String, Integer> entry : SpecificDiscount.serviceDiscount.entrySet())
		{
			System.out.print("Service " + entry.getKey());
			System.out.println(" has discount " + entry.getValue() + " $");
		}
	}
	public void refund() {
		System.out.println("Enter your transaction ID");
		int transId = scan.nextInt();
		System.out.println("Enter the amount to be refunded");
		double amout = scan.nextDouble();
		System.out.println("Enter service name");
		String serviceName = scan.next();
		User.requestRefund(transId, amout, serviceName, UserEmail);
	}
	public void chargeWallet() {
		
		CreditCard creditcard = new CreditCard();
		System.out.println("Enter the following: ");
		CreditCard.creditCardForm();
		String creditCardNum = scan.next(), exDate = scan.next();
		Wallet userWallet = Wallet.getUserWallet(UserEmail);
		System.out.println("Enter charge amount");
		double balance =scan.nextDouble();
		userWallet.chargeViaCreditCard(balance);
		
	}
	
	
	
	public void search() {
		double price = 0;
		ArrayList<String> providerForm = new ArrayList<String>(); //to retrieve fields of the form
		ArrayList<String> formAns = new ArrayList<String>(); //to store user's answers to the form
		System.out.println("Enter service name");
        String serve = scan.next();
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
						System.out.println("Overall discount will be preformed as it's your first transaction (10% off)");
						payMethod = new OverallDiscount(payMethod);
					}
					
					boolean serviceDiscount = SpecificDiscount.searchService(serve);
					if(serviceDiscount)
						payMethod = new SpecificDiscount(payMethod);							
					mobile.setPayMethod(payMethod);	
					disPrice = mobile.performPayMethod(price);
					
					System.out.println("Enter the following: ");
					CreditCard.creditCardForm();
					String creditCardNum = scan.next(), CCN = scan.next();
					
					System.out.println("payment with credit card is done successfully");
					transactionCounter++;
					counter++;
					Transactions.addTransaction(counter, disPrice, serve, UserEmail);
					System.out.println("Your transaction id is " + counter + "(must be known so you can request rufund)");
					
				}
				else if(payMthodNum == 2)//cash
				{
					Payment payMethod = new Cash();
					if(transactionCounter == 0) {
						System.out.println("Overall discount will be preformed as it's your first transaction (10% off)");
						payMethod = new OverallDiscount(payMethod);
					}
					
					boolean serviceDiscount = SpecificDiscount.searchService(serve);
					if(serviceDiscount)
						payMethod = new SpecificDiscount(payMethod);							
					mobile.setPayMethod(payMethod);	
					disPrice = mobile.performPayMethod(price);
					System.out.println("payment with cash is done successfully");
					transactionCounter++;
					counter++;
					Transactions.addTransaction(counter, disPrice, serve, UserEmail);
					System.out.println("Your transaction id is " + counter + "(must be known so you can request rufund)");
				}
				else if (payMthodNum ==3){ //wallet
					Wallet userWallet = Wallet.getUserWallet(UserEmail);
					if(transactionCounter == 0) {
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
						if(transactionCounter == 0)
							System.out.println("Overall discount will be preformed as it's your first transaction (10% off)");
						disPrice = mobile.performPayMethod(price);
						System.out.println("payment with wallet is done successfully");
						System.out.println("your wallet balance is " + userWallet.getBalance());
						transactionCounter++;
						counter++;
						Transactions.addTransaction(counter, disPrice, serve, UserEmail);
						System.out.println("Your transaction id is " + counter + "(must be known so you can request rufund)");
						check=false;
					}
					else 
						System.out.println("Your wallet balance is not enough");
				}
				
			}
			else 
				System.out.println("Fields of the form is incorrect");
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
				if(field == "Amount")
					price = Double.valueOf(ans);

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
				if(transactionCounter == 0) {
					System.out.println("Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				landline.setPayMethod(payMethod);	
				disPrice = landline.performPayMethod(price);
				
				System.out.println("Enter the following: ");
				CreditCard.creditCardForm();
				String creditCardNum = scan.next(), CCN = scan.next();
				
				System.out.println("payment with credit card is done successfully");
				
			
			}
			else if(payMthodNum == 2)//cash
			{
				Payment payMethod = new Cash();
				if(transactionCounter == 0) {
					System.out.println("Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				landline.setPayMethod(payMethod);	
				disPrice = landline.performPayMethod(price);
				System.out.println("payment with cash is done successfully");
			}
			transactionCounter++;
			counter++;
			Transactions.addTransaction(counter, disPrice, serve, UserEmail);//UserEmail
			System.out.println("Your transaction id is " + counter + "(must be known so you can request rufund)");
			
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
				if(field == "Amount")
					price = Double.valueOf(ans);
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
				if(transactionCounter == 0) {
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				Internet.setPayMethod(payMethod);	
				disPrice = Internet.performPayMethod(price);
				System.out.println("Enter the following: ");
				CreditCard.creditCardForm();
				String creditCardNum = scan.next(), CCN = scan.next();
				System.out.println("payment with credit card is done successfully");

			}
			else if(payMthodNum == 2)//cash
			{
				Payment payMethod = new Cash();
				if(transactionCounter == 0) {
					System.out.println("Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				Internet.setPayMethod(payMethod);	
				disPrice = Internet.performPayMethod(price);
				System.out.println("payment with cash is done successfully");
			}
			transactionCounter++;
			counter++;
			Transactions.addTransaction(counter, disPrice, serve, UserEmail);
			System.out.println("Your transaction id is " + counter + "(must be known so you can request rufund)");
			
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
				if(field == "Amount")
					price = Double.valueOf(ans);
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
				if(transactionCounter == 0) {
					System.out.println("Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				donation.setPayMethod(payMethod);	
				disPrice = donation.performPayMethod(price);
				System.out.println("Enter the following: ");
				CreditCard.creditCardForm();
				String creditCardNum = scan.next(), CCN = scan.next();
				System.out.println("payment with credit card is done successfully");

			}
			else if(payMthodNum == 2)//cash
			{
				Payment payMethod = new Cash();
				if(transactionCounter == 0) {
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serve);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				donation.setPayMethod(payMethod);	
				disPrice = donation.performPayMethod(price);
				System.out.println("payment with cash is done successfully");
			}
			transactionCounter++;
			counter++;
			Transactions.addTransaction(counter, disPrice, serve, UserEmail);
			System.out.println("Your transaction id is " + counter + "(must be known so you can request rufund)");
		}

	}

}


