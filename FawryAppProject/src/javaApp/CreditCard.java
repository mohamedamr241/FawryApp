package javaApp;

import java.util.ArrayList;
import java.util.Scanner;

public class CreditCard implements Payment{
	static ArrayList<String> creditCardInfo = new ArrayList<String>();
	public void pay(double amount) {
		System.out.println("payment with credit card is done successfully");
		
	}
	public CreditCard() {
	creditCardInfo.add("Credit card number");
	creditCardInfo.add("CCN");
	}
	public static void creditCardForm() {
	for(String s : creditCardInfo)
		System.out.println(s);
	
	}
}


