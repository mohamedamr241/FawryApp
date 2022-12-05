package javaApp;

import java.util.*;

public class Account {
	
	static Map<String, String> userAccounts = new HashMap<String, String>();
	static Map<String, Integer> userTransactionNumber = new HashMap<String, Integer>();
	static Map<String, User> users = new HashMap<String, User>();
	static Map<String, Wallet> userWallet = new HashMap<String, Wallet>();
	static Map<String, List<String>> transactions = new HashMap<String, List<String>>();
	static String adminEmail = "admin@gmail.com", adminPass = "0000";
	
}
