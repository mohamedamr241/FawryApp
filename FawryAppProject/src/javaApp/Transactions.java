package javaApp;

import java.util.*;

public class Transactions {
	static ArrayList<TransactionEntity> transactions = new ArrayList<TransactionEntity>();
	static Map<String, Integer> userTransactionNumber = new HashMap<String, Integer>();
	public static void addTransaction(int id, double amount, String serviceName, String email)
	{
		TransactionEntity obj = new TransactionEntity(id, amount, serviceName, email);
		transactions.add(obj);
	}

}
