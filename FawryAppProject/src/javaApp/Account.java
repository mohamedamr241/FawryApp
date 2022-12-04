package javaApp;

import java.util.*;

public class Account {
	
	static Map<String, String> userAccounts = new HashMap<String, String>();
	static Map<String, Wallet> userWallet = new HashMap<String, Wallet>();
	static String adminEmail = "admin@gmail.com", adminPass = "0000";
	public static Wallet getUserWallet(String email) {
		for (Map.Entry<String, Wallet> entry : Account.userWallet.entrySet())
		{
			if(entry.getKey().equals(email))
				return entry.getValue();
		}
		return null;
	}
}
