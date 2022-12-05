package javaApp;
import java.util.*;


public class User implements Observer{

	public ArrayList<String> notifications = new ArrayList<String>();
	public boolean signIn(String email, String pass)
	{
		
		for (Map.Entry<String, String> entry : Account.userAccounts.entrySet())
		{
			if(entry.getKey().equals(email) && entry.getValue().equals(pass))
				return true;
		}
		return false;
	}
	
	public String signUp(String username, String email, String pass, User u)
	{
		
		
		for (String e : Account.userAccounts.keySet())
		{
			if(e.equals(email))
				return "Email already exists";
		}
		Account.users.put(email,u);
		Transactions.userTransactionNumber.put(email,0);
		Account.userAccounts.put(email, pass);
		Account.userWallet.put(email, new Wallet());
		return "Account created successfully";
	}
	
	public static void requestRefund(int id, double amount, String service, String email) 
	{
		Obj obj = new Obj(id, amount, service, email);
		Admin.reqRefundList.add(obj);
		System.out.println("Your request refund is submited, it will be processed and you'll get notification");
	}

	@Override
	public void update(String note) {
		notifications.add(note);
	}
}



















