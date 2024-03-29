package javaApp;

import java.util.*;

public class Admin implements Subject{
	public static ArrayList<TransactionEntity> reqRefundList = new ArrayList<TransactionEntity>();
	static ArrayList<Observer> systemUsers = new ArrayList<Observer>();
	public boolean signIn(String email, String pass)
	{
		if(Account.adminEmail.equals(email)&& Account.adminPass.equals(pass))
			return true;
		return false;
	}
	
	public void addDiscount(String service, int dis)
	{
		SpecificDiscount.serviceDiscount.put(service,dis);
		Notify(dis+"% discount is applied on "+ service);
	}


	public boolean proccessRefund(TransactionEntity obj)
	{
		for(int i = 0; i < Transactions.transactions.size(); i++)
		{
			TransactionEntity o = Transactions.transactions.get(i);
			if(o.transId == obj.transId && o.amount == obj.amount && o.serviceName.equals(obj.serviceName) && o.userEmail.equals(obj.userEmail))
				return true;
		}
		return false;
	}
	public static void NotifyRefund(String email,String message) {
		for(Map.Entry<String, User> entry : Account.users.entrySet())
		{
			if(entry.getKey().equals(email)) {
				entry.getValue().notifications.add(message);
			}
		}
	}
	@Override
	public void Notify(String note) {
		for(int i=0;i<systemUsers.size();i++) {
			systemUsers.get(i).update(note);
		}
		
	}

	@Override
	public void subscribe(Observer ob) {
		systemUsers.add(ob);
	}
	
}
