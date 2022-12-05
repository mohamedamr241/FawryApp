package javaApp;
import java.util.*;

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
		for(Obj o : Admin.reqRefundList)
		{
			System.out.println(o);
			boolean ok = admin.proccessRefund(o);
			if(ok)
				System.out.println("Transcation ID and amount is correct");
			else
				System.out.println("Transcation ID and amount is incorrect");
			System.out.println("[1]Accept Refund Request");
			System.out.println("[2]reject Refund Request");
			int acc = scan.nextInt();
//			if(acc == 1)
				//notify user accepted
			
			

		}
		break;
	
	}
	
	}
	
	
	
	
	
}