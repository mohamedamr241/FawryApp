package javaApp;
import java.util.*;
import java.util.Scanner;

public class AdminSystemBoundry {
	
	public void Display()
	{
	Scanner scan = new Scanner(System.in);
	System.out.println("[1] Add Discount:");
	System.out.println("[2] Check List OF Refund Requests:");	
	int option = scan.nextInt();
	switch (option) {
	case 1:
		Map<String, Integer> map = new HashMap<String, Integer>() ;
		{
			{  
		        map.put("OverAll discount", 10);
		        map.put("Specific discount", 20);
			}
		}
	
		break;
		
	case 2:
		//Check  list of refund <string , int > -> <user email,amount of refund>
		break;
	
	}
	
	}
	
	
	
	
	
}