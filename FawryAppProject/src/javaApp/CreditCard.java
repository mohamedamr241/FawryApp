package javaApp;

import java.util.ArrayList;
import java.util.Scanner;

public class CreditCard implements Payment{
	Scanner scan = new Scanner(System.in);
	ArrayList<String> creditCardInfo = new ArrayList<String>();
	public CreditCard() {
		creditCardInfo.add("Credit card number: ");
		creditCardInfo.add("expired Date: ");
	}
	public void getData() {
		for(int i=0;i<creditCardInfo.size();i++) {
			System.out.println(creditCardInfo.get(i));
		}
	}
	public void pay(int amount) {
		getData();
		String num=scan.next();
		String date=scan.next();
		System.out.println("payment with credit card is done successfully");
		
	}
}
