package javaApp;

import java.util.*;

public class Admin {
	public static ArrayList<Obj> reqRefundList = new ArrayList<Obj>();
	public boolean signIn(String email, String pass)
	{
		if(Account.adminEmail.equals(email)&& Account.adminPass.equals(pass))
			return true;
		return false;
	}
	
	public void addDiscount(String service, int dis)
	{
		SpecificDiscount.serviceDiscount.put(service,dis);
	}


	public boolean proccessRefund(Obj obj)
	{
		boolean found = false;
		for(Obj o : Transactions.transactions)
		{
			if(o.transId == obj.transId && o.amount == obj.amount && o.serviceName.equals(obj.serviceName))
			{
				found = true;
				break;
			}
		}
		reqRefundList.remove(obj);
		return found;
	}
	
}
