package javaApp;

import java.util.ArrayList;
import java.util.Scanner;

public class CreditCard implements Payment{
	ArrayList<String> creditCardInfo = new ArrayList<String>();
//	public CreditCard() {
//		creditCardInfo.add("Credit card number");
//		creditCardInfo.add("expired Date");
//	}
//	public void creditCardForm() {
//		for(String s : creditCardInfo)
//			System.out.println(s);
//
//	}
	public void pay(int amount) {
		System.out.println("payment with credit card is done successfully");
		
	}
}
