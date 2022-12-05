package javaApp;

import java.util.*;

public class Transactions {
	static ArrayList<Obj> transactions = new ArrayList<Obj>();
	static Map<String, Integer> userTransactionNumber = new HashMap<String, Integer>();
	public static void addTransaction(int id, double amount, String serviceName, String email)
	{
		Obj obj = new Obj(id, amount, serviceName, email);
		transactions.add(obj);
	}

}
