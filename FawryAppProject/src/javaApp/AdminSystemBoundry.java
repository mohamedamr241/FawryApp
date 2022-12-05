package javaApp;
import java.util.*;

public class AdminSystemBoundry {
	Admin admin = new Admin();
	Scanner scan = new Scanner(System.in);
	public void Display()
	{

	boolean check = true;	
	while(check)
	{
		System.out.println("[1] Add Discount:");
		System.out.println("[2] Check List OF Refund Requests:");
		System.out.println("[3] exit:");	
		int option = scan.nextInt();
		switch (option) {
		case 1:
			
			System.out.println("Enter service name:");
			System.out.println("MobileRecharge");
			System.out.println("InternetPayment");
			System.out.println("Landline");
			System.out.println("Donations");
			String serviceName = scan.next();
			System.out.println("Enter discount Amount");
			int discountAmount = scan.nextInt();
			admin.addDiscount(serviceName, discountAmount);
			break;
		case 2:
			checkListOfRefunds();
			break;
		case 3:
			check = false;
			break;
		}
		
	}
	
	}
	public void checkListOfRefunds() {
		if(Admin.reqRefundList.size() == 0)
		{
			System.out.println("There's no refund requests");		
		}
		else {
			for(int i = 0; i < Admin.reqRefundList.size(); i++)
			{
				
				System.out.println(Admin.reqRefundList.get(i));
				boolean ok = admin.proccessRefund(Admin.reqRefundList.get(i));
				if(ok)
					System.out.println("Transcation ID and amount is correct");
				else
					System.out.println("Transcation ID and amount is incorrect");
				System.out.println("[1]Accept Refund Request");
				System.out.println("[2]reject Refund Request");
				
				int acc = scan.nextInt();
				if(acc == 1) {
					Wallet userWallet = Wallet.getUserWallet(Admin.reqRefundList.get(i).userEmail);
					double balance = userWallet.getBalance();
					userWallet.chargeViaCreditCard(Admin.reqRefundList.get(i).amount);
					Admin.NotifyRefund(Admin.reqRefundList.get(i).userEmail,"your refund request is accepted and your wallet balance is updated from " + balance + " to " + userWallet.getBalance());
				}
				else Admin.NotifyRefund(Admin.reqRefundList.get(i).userEmail,"your refund request is rejected"); 
			}
			Admin.reqRefundList.clear();
			
		}
	}
	
}
