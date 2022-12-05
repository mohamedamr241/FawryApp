package javaApp;
import java.util.*;


public class User {

	
	public boolean signIn(String email, String pass)
	{
		
		for (Map.Entry<String, String> entry : Account.userAccounts.entrySet())
		{
			if(entry.getKey().equals(email) && entry.getValue().equals(pass))
				return true;
		}
		return false;
	}
	
	public String signUp(String username, String email, String pass)
	{
		
		for (String e : Account.userAccounts.keySet())
		{
			if(e.equals(email))
				return "Email already exists";
		}
		Account.userAccounts.put(email, pass);
		Account.userWallet.put(email, new Wallet());
		return "Account created successfully";
	}
	
	public static void requestRefund(int id, double amount, String service) //related service and the amount to be refunded
	{
		Obj obj = new Obj(id, amount, service);
		Admin.reqRefundList.add(obj);
		System.out.println("Your request refund is submited");//(check notifications)
	}
}



















