package javaApp;

public class Wallet implements Payment{ //each user has a wallet that created auto 
	
	double balance = 0;
	public void pay(double amount) {
		balance-=amount;
		System.out.println("payment with wallet is done successfully");
	}
	
	public void chargeViaCreditCard(double balance)
	{
		this.balance += balance;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
}
