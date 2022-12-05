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
	
	public String signUp(String username, String email, String pass,User u)
	{
		
		for (String e : Account.userAccounts.keySet())
		{
			if(e.equals(email))
				return "Email already exists";
		}
		Account.userAccounts.put(email, pass);
		Account.users.put(email, u);
		Account.userTransactionNumber.put(email, 0);
		Account.userWallet.put(email, new Wallet());
		//Admin.systemUsers.add();
		return "Account created successfully";
	}
	public void update(String note) {
		notifications.add(note);
	}
}