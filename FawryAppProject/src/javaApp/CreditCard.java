package javaApp;

import java.util.ArrayList;

public class CreditCard implements Payment{
	ArrayList<String> creditCardInfo = new ArrayList<String>();
	public CreditCard() {
		creditCardInfo.add("Credit card number: ");
		creditCardInfo.add("expired Date: ");
	}
	public ArrayList<String> getData() {
		return creditCardInfo;
	}
	public void pay(int amount) {
		System.out.println("payment with credit card is done successfully");
		
	}
}
