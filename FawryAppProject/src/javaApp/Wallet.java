package javaApp;

import java.util.Map;

public class Wallet implements Payment{ //each user has a wallet that created auto 
	
	private double balance = 0;
	public void pay(double amount) {
		balance-=amount;
		System.out.println("payment with wallet is done successfully");
	}
	
	public void chargeViaCreditCard(double balance)
	{
		this.balance += balance;
		System.out.println("wallet is charged with " + balance +" successfully.");
	}
	
	public double getBalance()
	{
		return balance;
	}
	public static Wallet getUserWallet(String email) {
		for (Map.Entry<String, Wallet> entry : Account.userWallet.entrySet())
		{
			if(entry.getKey().equals(email))
				return entry.getValue();
		}
		return null;
	}
	
}
