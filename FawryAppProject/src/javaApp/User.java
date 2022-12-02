package javaApp;
import java.util.*;


public class User {

	ArrayList<String> emails = new ArrayList<String>();
	ArrayList<String> passwords = new ArrayList<String>();
	public boolean signIn(String email, String pass)
	{
		for (String e : emails)
		{
			if(emails.contains(e) && passwords.contains(pass))
				return true;
		}
		return false;
	}
	
	public String signUp(String username, String email, String pass)
	{
		
		for (String e : emails)
		{
			if(emails.contains(e))
				return "Email already exists";
		}
		emails.add(email);
		passwords.add(pass);
		return "Account created successfully";
	}
}