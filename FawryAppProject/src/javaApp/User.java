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
}