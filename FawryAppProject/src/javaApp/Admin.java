package javaApp;

public class Admin {
	
	Account adminAcc;
	public boolean signIn(String email, String pass)
	{
		if(adminAcc.adminEmail.equals(email)&& adminAcc.adminPass.equals(pass))
			return true;
		return false;
	}
	

}
