package javaApp;
import java.util.*;
import java.util.Scanner;

public class AdminSystemBoundry {
	Admin admin = new Admin();
	Scanner scan = new Scanner(System.in);
	public void Display()
	{

	System.out.println("[1] Add Discount:");
	System.out.println("[2] Check List OF Refund Requests:");	
	int option = scan.nextInt();
	switch (option) {
	case 1:
		
		System.out.println("Enter service name");
		System.out.println("MobileRecharge");
		System.out.println("Internet payment service");
		System.out.println("Landline service");
		System.out.println("Donations");
        String serviceName = " ";
        serviceName = scan.nextLine();
        serviceName +=scan.nextLine();
		System.out.println("Enter discount Amount");
		int discountAmount = scan.nextInt();
		admin.addDiscount(serviceName, discountAmount);
		break;
	case 2:
		//Check  list of refund <string , int > -> <user email,amount of refund>
		break;
	
	}
	
	}
	
	
	
	
	
}